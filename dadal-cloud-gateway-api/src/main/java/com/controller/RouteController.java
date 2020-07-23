/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月3日
 * @version: V1.0
 */

package com.controller;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.filter.FilterDefinition;
import org.springframework.cloud.gateway.handler.predicate.PredicateDefinition;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import com.entity.GatewayFilterDefinition;
import com.entity.GatewayPredicateDefinition;
import com.entity.GatewayRouteDefinition;
import com.service.DynamicRouteServiceImpl;

import reactor.core.publisher.Mono;

/**
 * @Description: Route Rest Controller
 * @author: Frankjiu
 * @date: 2020年5月3日
 */
@RestController
@RequestMapping("/route")
public class RouteController {

	private static final Logger logger = LoggerFactory.getLogger(RouteController.class);

	@Autowired
	private DynamicRouteServiceImpl dynamicRouteService;

	//add route
	@PostMapping("/add")
	public String add(@RequestBody GatewayRouteDefinition gwdefinition) {
		String flag = "fail";
		try {
			RouteDefinition definition = assembleRouteDefinition(gwdefinition);
			flag = this.dynamicRouteService.add(definition);
		} catch (Exception e) {
			logger.info("errmsg:{}", e.getMessage(), e);
		}
		return flag;
	}

	//delete route
	@DeleteMapping("/routes/{id}")
	public Mono<ResponseEntity<Object>> delete(@PathVariable String id) {
		try {
			return this.dynamicRouteService.delete(id);
		} catch (Exception e) {
			logger.info("errmsg:{}", e.getMessage(), e);
		}
		return null;
	}

	//update route
	@PostMapping("/update")
	public String update(@RequestBody GatewayRouteDefinition gwdefinition) {
		RouteDefinition definition = assembleRouteDefinition(gwdefinition);
		return this.dynamicRouteService.update(definition);
	}

	//convert parameter to route-object
	private RouteDefinition assembleRouteDefinition(GatewayRouteDefinition gatewaydefinition) {
		RouteDefinition definition = new RouteDefinition();
		definition.setId(gatewaydefinition.getId());
		definition.setOrder(gatewaydefinition.getOrder());

		//set assert
		List<PredicateDefinition> pdList = new ArrayList<>();
		List<GatewayPredicateDefinition> gatewayPredicateDefinitionList = gatewaydefinition.getPredicates();
		for (GatewayPredicateDefinition gpDefinition : gatewayPredicateDefinitionList) {
			PredicateDefinition predicate = new PredicateDefinition();
			predicate.setArgs(gpDefinition.getRule());
			predicate.setName(gpDefinition.getName());
			pdList.add(predicate);
		}
		definition.setPredicates(pdList);

		//set filter
		List<FilterDefinition> filters = new ArrayList<>();
		List<GatewayFilterDefinition> gatewayFilters = gatewaydefinition.getFilters();
		for (GatewayFilterDefinition filterDefinition : gatewayFilters) {
			FilterDefinition filter = new FilterDefinition();
			filter.setName(filterDefinition.getName());
			filter.setArgs(filterDefinition.getRule());
			filters.add(filter);
		}
		definition.setFilters(filters);

		URI uri = null;
		if (gatewaydefinition.getUri().startsWith("http")) {
			uri = UriComponentsBuilder.fromHttpUrl(gatewaydefinition.getUri()).build().toUri();
		} else {
			// use this method below if the uri is lb://gateway-consumer
			uri = URI.create(gatewaydefinition.getUri());
		}
		definition.setUri(uri);
		return definition;
	}
}
