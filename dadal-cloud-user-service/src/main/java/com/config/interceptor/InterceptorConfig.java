package com.config.interceptor;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	/*@Bean
	public LogsInterceptor logsInterceptor() {
		return new LogsInterceptor();
	}
	
	*//**
	 * 注册拦截器
	 *//*
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
	// 登录拦截器
	registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/checkLogin", "/logout", "/code/getCode");
	
	// 加入拦截拦截器栈
	super.addInterceptors(registry);
	}*/

}
