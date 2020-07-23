/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月16日
 * @version: V1.0
 */
package com;

import org.activiti.api.process.model.ProcessDefinition;
import org.activiti.api.process.model.ProcessInstance;
import org.activiti.api.process.model.builders.ProcessPayloadBuilder;
import org.activiti.api.process.runtime.ProcessRuntime;
import org.activiti.api.runtime.shared.query.Page;
import org.activiti.api.runtime.shared.query.Pageable;
import org.activiti.api.task.model.Task;
import org.activiti.api.task.model.builders.TaskPayloadBuilder;
import org.activiti.api.task.runtime.TaskRuntime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.config.SecurityUtil;

/**
 * @Description: Actviti Application Test
 * @author: Frankjiu
 * @date: 2020年5月16日
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class ActvitiApplicationTest {

	private Logger logger = LoggerFactory.getLogger(ActvitiApplicationTest.class);

	/**
	 * 实现流程定义相关操作
	 */
	@Autowired
	private ProcessRuntime processRuntime;

	/**
	 * 实现任务相关操作
	 */
	@Autowired
	private TaskRuntime taskRuntime;

	/**
	 * SpringSecurity 相关工具类
	 */
	@Autowired
	private SecurityUtil securityUtil;

	/**
	 * 查看流程定义(流程定义自动部署)
	 */
	@Test
	public void contextLoads() {
		securityUtil.logInAs("salaboy");
		Page<ProcessDefinition> processDefinitionPage = processRuntime.processDefinitions(Pageable.of(0, 10));
		logger.info("可用的流程定义数量:{}", processDefinitionPage.getTotalItems());
		for (ProcessDefinition pd : processDefinitionPage.getContent()) {
			logger.info("流程定义:{}", pd);
		}
	}

	/**
	 * 启动流程实例
	 */
	@Test
	public void testStartProcess() {
		securityUtil.logInAs("salaboy");
		ProcessInstance processInstance = processRuntime
				.start(ProcessPayloadBuilder.start().withProcessDefinitionKey("myProcess_1").build());
		logger.info("流程实例已启动, 流程实例ID为:{}", processInstance.getId());
	}

	/**
	 * 查询任务，并执行(完成)任务
	 */
	@Test
	public void testTask() {
		// 认证(模拟指定)
		securityUtil.logInAs("ryandawsonuk");
		// 分页查询任务列表
		Page<Task> taskPage = taskRuntime.tasks(Pageable.of(0, 10));
		if (taskPage.getTotalItems() > 0) {
			logger.info(">>>初始查询任务数量:{}", taskPage.getContent().size());
			for (Task task : taskPage.getContent()) {
				logger.info("任务:{}", task);
				// 拾取任务 candidateGroups="activitiTeam"
				taskRuntime.claim(TaskPayloadBuilder.claim().withTaskId(task.getId()).build());
				// 执行任务
				taskRuntime.complete(TaskPayloadBuilder.complete().withTaskId(task.getId()).build());
			}
		}

		// 检查任务完成情况--剩余任务数=0
		taskPage = taskRuntime.tasks(Pageable.of(0, 10));
		if (taskPage.getTotalItems() > 0) {
			logger.info(">>>===最后查询任务数量:{}", taskPage.getContent().size());
		}
	}

}
