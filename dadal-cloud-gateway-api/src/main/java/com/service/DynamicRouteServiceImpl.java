/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年5月3日
 * @version: V1.0
 */

package com.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.gateway.event.RefreshRoutesEvent;
import org.springframework.cloud.gateway.route.RouteDefinition;
import org.springframework.cloud.gateway.route.RouteDefinitionWriter;
import org.springframework.cloud.gateway.support.NotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.ApplicationEventPublisherAware;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import reactor.core.publisher.Mono;

/**
 * @Description: Dynamic Route Service
 * @author: Frankjiu
 * @date: 2020年5月3日
 */
@Service
public class DynamicRouteServiceImpl implements ApplicationEventPublisherAware {

	@Autowired
	private RouteDefinitionWriter routeDefinitionWriter;
	private ApplicationEventPublisher publisher;

	@Override
	public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
		this.publisher = applicationEventPublisher;
	}

	// add route
	public String add(RouteDefinition definition) {
		routeDefinitionWriter.save(Mono.just(definition)).subscribe();
		this.publisher.publishEvent(new RefreshRoutesEvent(this));
		return "add successed!";
	}

	// update route
	public String update(RouteDefinition definition) {
		try {
			delete(definition.getId());
		} catch (Exception e) {
			return "update fail, can't find route:" + definition.getId();
		}
		try {
			routeDefinitionWriter.save(Mono.just(definition)).subscribe();
			this.publisher.publishEvent(new RefreshRoutesEvent(this));
			return "update successed!";
		} catch (Exception e) {
			return "update failed";
		}
	}

	// delete route
	public Mono<ResponseEntity<Object>> delete(String id) {
		return this.routeDefinitionWriter.delete(Mono.just(id)).then(Mono.defer(() -> {
			return Mono.just(ResponseEntity.ok().build());
		})).onErrorResume((t) -> {
			return t instanceof NotFoundException;
		}, (t) -> {
			return Mono.just(ResponseEntity.notFound().build());
		});
	}
}
