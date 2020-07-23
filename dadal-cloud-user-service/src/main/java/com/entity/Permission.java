/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.entity;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: Permission
 * @author: Frankjiu
 * @date: 2020年6月12日
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class Permission {
	private Integer id;
	private String permissionName;
	private Date createTime;
}