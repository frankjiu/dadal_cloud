/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年7月3日
 * @version: V1.0
 */

package com.config;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月3日
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
	private Long age;
	private String name;
}