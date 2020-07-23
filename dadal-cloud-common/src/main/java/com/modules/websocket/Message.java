/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.appconfig.websocket   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月8日上午8:43:37
 * @version V1.0
 */

package com.modules.websocket;

import java.io.Serializable;

import lombok.Data;

/**
 * WebSocket Message Entity
 * 
 * @author: Frankjiu
 * @date: 2020年4月8日 上午8:43:37
 */
@Data
public class Message implements Serializable {

	private static final long serialVersionUID = 1L;
	private String name;
	private String from;
	private String to;
	private String content;

}
