/**
 * All rights Reserved, Designed By www.xcompany.com
 * 
 * @Package com.funcbean.shiro
 * @Description: TODO 描述
 * @author: Frankjiu
 * @date: 2020年4月7日上午11:19:25
 * @version V1.0
 */

package com.modules.auth.shiro;

import org.apache.shiro.realm.Realm;
import org.apache.shiro.realm.text.TextConfigurationRealm;
import org.apache.shiro.spring.web.config.DefaultShiroFilterChainDefinition;
import org.apache.shiro.spring.web.config.ShiroFilterChainDefinition;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * shiro configuration
 * 
 * @author: Frankjiu
 * @date: 2020年4月7日 上午11:19:25
 */
@Configuration
public class ShiroConfig {

	@Bean
	public Realm realm() {
		TextConfigurationRealm realm = new TextConfigurationRealm();
		realm.setUserDefinitions("admin=admin,admin\n frank=123456,user");
		realm.setRoleDefinitions("admin=read,write\n user=read");
		return realm;
	}

	@Bean
	public ShiroFilterChainDefinition shiroFilterChainDefinition() {
		DefaultShiroFilterChainDefinition chainDef = new DefaultShiroFilterChainDefinition();
		chainDef.addPathDefinition("/login", "anon");
		chainDef.addPathDefinition("/doLogin", "anon");
		chainDef.addPathDefinition("/logout", "logout");
		chainDef.addPathDefinition("/**", "authc");
		return chainDef;
	}

	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * 引入spring-aop后,在Controller类中加入shiro注解会导致请求映射失效(return 404);此配置用以解决该问题.
	 */
	@Bean
	public static DefaultAdvisorAutoProxyCreator getDefaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setUsePrefix(true);
		return defaultAdvisorAutoProxyCreator;
	}

}
