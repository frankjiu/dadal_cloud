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
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: Role
 * @author: Frankjiu
 * @date: 2020年6月12日
 */
@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Role {
	private Integer id;
	private String roleName;
	private Date createTime;
}