package com.config;

import org.springframework.boot.SpringBootConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import com.controller.LogsInterceptor;

/**
 * @Description: 拦截器注册
 * @author: Frankjiu
 * @date: 2020年6月7日
 */
@SuppressWarnings("deprecation")
@SpringBootConfiguration
public class InterceptorConfig extends WebMvcConfigurerAdapter {

	@Bean
	public LogsInterceptor logsInterceptor() {
		return new LogsInterceptor();
	}

	/**
	 * 注册拦截器
	 */
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// 登录拦截配置
		/*registry.addInterceptor(new LoginInterceptor()).addPathPatterns("/**").excludePathPatterns("/login", "/checkLogin", "/exit",
				"/ueditor/jsp/controller.jsp", "/ueditor/jsp/config.json", "/registered", "/loginUser/registered",
				"/device/getDeviceById", "/loginUser/login", "/showQr/download", "/deviceSubtype/getSubTypeStates",
				"/loginUser/register", //游客注册
				"/messageInfo/updateDoorState", //修改门禁状态
				"/messageInfo/getDoorMsg" //扫码开门记录
		);
		*/

		// 日志拦截配置
		registry.addInterceptor(logsInterceptor()).addPathPatterns("/**");
		super.addInterceptors(registry);
	}

}
