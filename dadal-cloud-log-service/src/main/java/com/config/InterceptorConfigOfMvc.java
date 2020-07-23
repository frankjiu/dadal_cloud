package com.config;

import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

@SuppressWarnings("deprecation")
public class InterceptorConfigOfMvc extends WebMvcConfigurerAdapter {

	/*@Bean
	public LogsInterceptor logsInterceptor() {
		return new LogsInterceptor();
	}
	
	*//**
		 * 注册拦截器
		 *//*
		@Override
		public void addInterceptors(InterceptorRegistry registry) {
		
		// 日志拦截器
		registry.addInterceptor(logsInterceptor()).addPathPatterns("/**");
		
		// 加入拦截拦截器栈
		super.addInterceptors(registry);
		}*/

}
