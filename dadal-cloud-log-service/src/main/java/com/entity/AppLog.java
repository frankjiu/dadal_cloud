/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年6月1日
 * @version: V1.0
 */

package com.entity;

import java.util.Date;

import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.Data;

/**
 * @Description: 日志实体类
 * @author: Frankjiu
 * @date: 2020年6月1日
 */
@Component
@Data
public class AppLog {

	/** 日志id */
	private Integer logId;

	/** 日志时间 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date logTime;

	/** 日志查询起始时间 */
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date startLogTime;

	/** 日志查询截止时间 */
	@Transient
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	private Date endLogTime;

	/** 线程名 */
	private String threadName;

	/** 日志级别 */
	private String logLeval;

	/** 类名 */
	private String className;

	/** 日志详情 */
	private String logInfo;

	/** 当前页 */
	@Transient
	private int pageNum;

}
