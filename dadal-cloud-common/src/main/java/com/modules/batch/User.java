/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.entity   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午11:51:11
 * @version V1.0
 */

package com.modules.batch;

import java.io.Serializable;

import lombok.Data;

/**
 * Batch User Data
 * 
 * @author: Frankjiu
 * @date: 2020年4月2日 下午11:51:11
 */
@Data
public class User implements Serializable {

	private static final long serialVersionUID = 1L;

	private Integer id;
	private String username;
	private String address;
	private String gender;

}
