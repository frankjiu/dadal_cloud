/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年6月1日
 * @version: V1.0
 */

package com.controller;

import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.alibaba.fastjson.JSONObject;
import com.entity.AppLog;
import com.entity.PageModel;
import com.service.LogService;
import com.util.LogOperation;
import com.util.SysLog;

/**
 * @Description: 日志Controller
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
@Controller
@RequestMapping("/log")
public class LogController {

	private static Logger logger = Logger.getLogger(LogController.class);

	@Autowired
	LogService logService;

	@Autowired
	FileParse fileParse;

	/**
	 * 日志清理(测试用)
	 */
	@GetMapping("/delete")
	@ResponseBody
	public String delete() {
		try {
			logService.delete();
			return "清理成功!";
		} catch (Exception e) {
			logger.info(e);
			return "清理异常!";
		}
	}

	/**
	 * 日志记录
	 */
	@PostMapping("/record")
	@ResponseBody
	public String insert() {
		logger.info(">>>>>> 开始记录 >>>");
		AppLog appLog = new AppLog();
		appLog.setLogInfo("testinfoClient");
		appLog.setLogLeval("infoClient");
		appLog.setThreadName("mainClient");
		appLog.setLogTime(new Date());
		appLog.setClassName("testClassClient");

		try {
			int num = logService.insert(appLog);
			if (num > 0) {
				return "记录成功!";
			} else {
				return "记录失败!";
			}
		} catch (Exception e) {
			logger.info(e);
			return "记录异常!";
		}
	}

	/**
	 * 日志文件数据批量导入
	 */
	@PostMapping
	@ResponseBody
	public String insertBatch() {
		logger.info(">>>>>> 开始解析文件... >>>");
		List<AppLog> list = fileParse.parse();
		logger.info(">>>>>> 文件解析成功! >>>");
		try {
			logger.info(">>>>>> 开始批量导入... >>>");
			int num = logService.insertBatch(list);
			if (num == list.size()) {
				return "批量导入成功!";
			} else {
				return "批量导入失败!";
			}
		} catch (Exception e) {
			logger.info(e);
			return "批量导入异常!";
		}

	}

	/**
	 * 分页列表查询(旧版)
	 */
	@GetMapping("/toPageList")
	public ModelAndView toPageList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, AppLog appLog) {
		// 查询总记录数
		int totalCount = logService.getTotal(appLog);
		// 创建PageModel对象
		PageModel<AppLog> pageModel = new PageModel<>(pageNum, pageSize, totalCount);
		// 数据查询
		List<AppLog> data = logService.getList(pageModel.getStartIndex(), pageModel.getPageSize(), appLog);
		pageModel.setList(data);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageModel", pageModel);
		mv.setViewName("list");
		return mv;
	}

	/**
	 * 条件查询分页数据
	 */
	@GetMapping("/getPageData")
	@ResponseBody
	public String getPageData(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, AppLog appLog) {
		int reqPageNum = appLog.getPageNum();
		if (reqPageNum != 0) {
			pageNum = reqPageNum;
		}
		// 查询总记录数
		int totalCount = logService.getTotal(appLog);
		// 创建PageModel对象
		PageModel<AppLog> pageModel = new PageModel<>(pageNum, pageSize, totalCount);
		// 数据查询
		List<AppLog> data = logService.getList(pageModel.getStartIndex(), pageModel.getPageSize(), appLog);
		pageModel.setList(data);

		JSONObject obj = new JSONObject();
		obj.put("pageModel", pageModel);
		return obj.toJSONString();
	}

	/**
	 * 查询总记录数
	 */
	@GetMapping("/totalCount")
	@ResponseBody
	@SysLog(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_SYSTEM, remark = "日志总记录数查询-测试记录")
	public int totalCount(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, AppLog appLog) {

		// 查询总记录数
		int totalCount = logService.getTotal(appLog);
		PageModel<AppLog> pageModel = new PageModel<>(pageNum, pageSize, totalCount);
		return pageModel.getTotalPages();
	}

	/**
	 * 分页列表查询
	 */
	@GetMapping
	@SysLog(operationType = LogOperation.OP_QUERY, operationModule = LogOperation.WP_SYSTEM, remark = "日志分页列表查询-测试记录")
	public ModelAndView getList(@RequestParam(value = "pageNum", defaultValue = "0") int pageNum,
			@RequestParam(value = "pageSize", defaultValue = "10") int pageSize, AppLog appLog) {
		// 查询总记录数
		int totalCount = logService.getTotal(appLog);
		// 创建PageModel对象
		PageModel<AppLog> pageModel = new PageModel<>(pageNum, pageSize, totalCount);
		// 数据查询
		List<AppLog> data = logService.getList(pageModel.getStartIndex(), pageModel.getPageSize(), appLog);
		pageModel.setList(data);

		ModelAndView mv = new ModelAndView();
		mv.addObject("pageModel", pageModel);
		mv.setViewName("pagelist");
		return mv;
	}

}
