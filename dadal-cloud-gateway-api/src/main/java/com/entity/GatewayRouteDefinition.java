/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com   
 * @author: Frankjiu
 * @date: 2020年5月3日
 * @version: V1.0
 */

package com.entity;

import java.util.List;

import com.google.common.collect.Lists;

/**
 * @Description: Gateway Route Definition
 * @author: Frankjiu
 * @date: 2020年5月3日
 */
public class GatewayRouteDefinition {

	//route Id
	private String id;
	//route-assert List
	private List<GatewayPredicateDefinition> predicates = Lists.newArrayList();
	//route-filter List
	private List<GatewayFilterDefinition> filters = Lists.newArrayList();
	//route-forward uri
	private String uri;
	//route-execution-order
	private int order = 0;

	/**
	 * 获取 id
	 */
	public String getId() {
		return id;
	}

	/**
	 * 设置 id
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * 获取 predicates
	 */
	public List<GatewayPredicateDefinition> getPredicates() {
		return predicates;
	}

	/**
	 * 设置 predicates
	 */
	public void setPredicates(List<GatewayPredicateDefinition> predicates) {
		this.predicates = predicates;
	}

	/**
	 * 获取 filters
	 */
	public List<GatewayFilterDefinition> getFilters() {
		return filters;
	}

	/**
	 * 设置 filters
	 */
	public void setFilters(List<GatewayFilterDefinition> filters) {
		this.filters = filters;
	}

	/**
	 * 获取 uri
	 */
	public String getUri() {
		return uri;
	}

	/**
	 * 设置 uri
	 */
	public void setUri(String uri) {
		this.uri = uri;
	}

	/**
	 * 获取 order
	 */
	public int getOrder() {
		return order;
	}

	/**
	 * 设置 order
	 */
	public void setOrder(int order) {
		this.order = order;
	}

}
