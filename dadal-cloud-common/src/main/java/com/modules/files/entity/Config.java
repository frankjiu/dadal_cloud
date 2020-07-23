/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.entity   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月1日上午11:07:07
 * @version V1.0
 */

package com.modules.files.entity;

import java.util.Date;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

/**
 * @author: Frankjiu
 * @date: 2020年4月1日 上午11:07:07 读取配置数据到Config类
 */
@Component
@Data
@ConfigurationProperties(prefix = "config")
public class Config {

	/** IP地址 */
	private String ip;

	/** 端口 */
	private int port;

	/** 文件上传保存路径 */
	private String uploadFilePath;

	/** 更新时间 */
	@JsonIgnore
	private Date updateTime;

	/** 创建时间 */
	@JsonFormat(pattern = "yyyy-MM-dd")
	private Date createTime;

}
