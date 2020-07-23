/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年5月3日
 * @version: V1.0
 */

package com.entity;

import java.util.Map;

import com.google.common.collect.Maps;

/**
 * @Description: Gateway Predicate Definition
 * @author: Frankjiu
 * @date: 2020年5月3日
 */
public class GatewayPredicateDefinition {

	//route-assert name
	private String name;

	//route-assert rule
	private Map<String, String> rule = Maps.newLinkedHashMap();

	/**
	 * 获取 name
	 */
	public String getName() {
		return name;
	}

	/**
	 * 设置 name
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * 获取 rule
	 */
	public Map<String, String> getRule() {
		return rule;
	}

	/**
	 * 设置 rule
	 */
	public void setRule(Map<String, String> rule) {
		this.rule = rule;
	}

}
