/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.entity   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: User
 * @author: Frankjiu
 * @date: 2020年6月12日
 */

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {
	private Integer id;
	private String username;
	private String password;
	private String salt;
}