/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年5月16日
 * @version: V1.0
 */

package com.controller;

import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.config.SecurityUtil;

/**
 * @Description: Activiti Controller
 * @author: Frankjiu
 * @date: 2020年5月16日
 */
@RestController
public class ActivitiController {

	private Logger logger = LoggerFactory.getLogger(ActivitiController.class);

	@Autowired
	private ProcessRuntime processRuntime;

	@Autowired
	private TaskRuntime taskRuntime;

	@Autowired
	private SecurityUtil securityUtil;

	/**
	 * 手动启动流程实例以 添加任务
	 */
	@GetMapping("/create")
	public void startProcessManually() {
		securityUtil.logInAs("salaboy");
		ProcessInstance processInstance = processRuntime
				.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1").build());
		logger.info("流程实例已启动--任务ID:{}", processInstance.getId());
	}

	/**
	 * 注意: 测试前需要手动启动一次任务实例添加任务, 这里直接调用 startProcessManually 方法.
	 * 多个子任务需要多次调用该方法处理执行
	 */
	@GetMapping("/test")
	public String testTask() {
		// 认证(模拟指定)
		securityUtil.logInAs("ryandawsonuk");
		// 分页查询任务列表
		Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
		if (taskPage.getTotalItems() > 0) {
			logger.info(">>>初始查询任务数量:{}", taskPage.getContent().size());
			for (Task task : taskPage.getContent()) {
				logger.info("当前任务:{}", task);
				// 拾取任务 candidateGroups="activitiTeam"
				taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
				// 执行任务
				taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
				try {
					Thread.sleep(1000);
				} catch (Exception e) {
					logger.info(e.getMessage(), e);
				}
			}
		}
		// 检查任务完成情况--剩余任务数=0
		taskPage = taskRuntime.tasks(Pageable.of(0, 10));
		if (taskPage.getTotalItems() > 0) {
			logger.info(">>>结束查询任务数量:{}", taskPage.getContent().size());
			for (Task task : taskPage.getContent()) {
				logger.info("剩余任务:{}", task);
			}
		} else {
			logger.info("任务已全部完成!");
		}

		return "Ok, tasks executed successfully!";
	}

}
