/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年6月12日
 * @version: V1.0
 */

package com.config;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Properties;

import javax.servlet.Filter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.MemoryConstrainedCacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.filter.authc.FormAuthenticationFilter;
import org.apache.shiro.web.mgt.CookieRememberMeManager;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
//import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.web.servlet.SimpleCookie;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import at.pollux.thymeleaf.shiro.dialect.ShiroDialect;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年6月12日
 */

@Configuration
public class ShiroConfig {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Bean("shiroFilterFactoryBean")
	public ShiroFilterFactoryBean shirFilter(@Qualifier("securityManager") SecurityManager securityManager) {
		logger.info("启动shiroFilter--时间是：" + new Date());
		ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
		shiroFilterFactoryBean.setSecurityManager(securityManager);
		//shiro拦截器
		Map<String, String> filterChainDefinitionMap = new LinkedHashMap<String, String>();
		//<!-- authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问-->
		//<!-- 过滤链定义，从上向下顺序执行，一般将/**放在最为下边 -->
		Map<String, Filter> filterMap = new LinkedHashMap<>();
		filterMap.put("MyRememberFilter", new MyRememberFilter());

		shiroFilterFactoryBean.setFilters(filterMap);

		// 如果不设置默认会自动寻找Web工程根目录下的"/login"页面，即本文使用的login.html
		shiroFilterFactoryBean.setLoginUrl("/login");
		// 登录成功后要跳转的链接
		shiroFilterFactoryBean.setSuccessUrl("/main");
		//错误页面，认证不通过跳转
		shiroFilterFactoryBean.setUnauthorizedUrl("/error");
		//未授权界面
		shiroFilterFactoryBean.setUnauthorizedUrl("/403");

		// 配置不被拦截的资源及链接
		filterChainDefinitionMap.put("/static/**", "anon");
		// 退出过滤器
		filterChainDefinitionMap.put("/logout", "logout");
		//开启注册页面不需要权限
		filterChainDefinitionMap.put("/user/login", "anon");
		filterChainDefinitionMap.put("/user/register", "anon");

		//配置需要认证权限的
		filterChainDefinitionMap.put("/**", "authc");

		shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionMap);
		return shiroFilterFactoryBean;
	}

	/**
	 * 配置shiro的生命周期
	 *
	 * @return
	 */
	@Bean
	public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
		return new LifecycleBeanPostProcessor();
	}

	/**
	 * 定义加密规则 存入密码时也必须加密
	 */
	@Bean
	public HashedCredentialsMatcher myMatcher() {
		HashedCredentialsMatcher matcher = new HashedCredentialsMatcher();
		matcher.setHashAlgorithmName("SHA-256");
		// true means hex encoded, false means base64 encoded
		matcher.setStoredCredentialsHexEncoded(false);
		matcher.setHashIterations(10000);
		return matcher;
	}

	public class MyRememberFilter extends FormAuthenticationFilter {
		protected boolean isAccessAllowed(HttpServletRequest request, HttpServletResponse response, Object mappedValue) {
			Subject subject = getSubject(request, response);
			if (!subject.isAuthenticated() && subject.isRemembered()) {
				if (subject.getSession().getAttribute("user") == null && subject.getPrincipal() != null) {
					subject.getSession().setAttribute("user", subject.getPrincipal());
				}

			}
			return subject.isAuthenticated() || subject.isRemembered();
		}
	}
	/* private class MyMatcher extends HashedCredentialsMatcher {
	    @Override
	    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
	        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
	        String pwd = encrypt(String.valueOf(usernamePasswordToken.getPassword()));
	        String mysqlpwd = (String) info.getCredentials();
	        logger.info("密码"+mysqlpwd);
	        logger.info("密码"+pwd);
	        return this.equals(pwd, mysqlpwd);
	    }
	
	    //将传进来的密码进行加密的方法
	    private String encrypt(String data) {
	        // 加密
	        String salt = UUID.randomUUID().toString();
	        String s = new Sha256Hash(data, salt, MyConstant.INTERCOUNT).toBase64();
	        return s;
	    }
	}*/

	/**
	 * 自定义身份认证Realm（包含用户名密码校验，权限校验等）
	 */
	@Bean
	public ShiroRealm myShiroRealm() {
		ShiroRealm myShiroRealm = new ShiroRealm();
		myShiroRealm.setCredentialsMatcher(myMatcher());
		return myShiroRealm;
	}

	/**
	 * 使用shiro 支持thymeleaf 模版引擎
	 */
	@Bean
	public ShiroDialect shiroDialect() {
		return new ShiroDialect();
	}

	/**
	 * 配置记住我Cookie对象参数，rememberMeCookie()方法是设置Cookie的生成模版
	 */
	public SimpleCookie rememberMeCookie() {
		//这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("rememberMe");
		//cookie生效时间为10秒
		simpleCookie.setMaxAge(100000);
		return simpleCookie;
	}

	/**
	 * 配置Cookie管理对象，rememberMeManager()方法是生成rememberMe管理器
	 */
	@Bean
	public CookieRememberMeManager rememberMeManager() {
		CookieRememberMeManager cookieRememberMeManager = new CookieRememberMeManager();
		cookieRememberMeManager.setCookie(rememberMeCookie());
		return cookieRememberMeManager;
	}

	/**
	 * 配置sessionCookie对象参数，sessionCookie()方法是设置Cookie的生成模版
	 */
	public SimpleCookie sessionIdCookie() {
		//这个参数是cookie的名称，对应前端的checkbox的name=rememberMe
		SimpleCookie simpleCookie = new SimpleCookie("JSESSIONID");
		//只允许http请求访问cookie
		simpleCookie.setHttpOnly(true);
		//cookie生效时间为10秒 默认为-1
		simpleCookie.setMaxAge(-1);
		return simpleCookie;
	}

	/**
	 * 配置Cookie管理对象，rememberMeManager()方法是生成rememberMe管理器
	 */
	@Bean
	public DefaultWebSessionManager sessionManager() {
		DefaultWebSessionManager webSessionManager = new DefaultWebSessionManager();
		webSessionManager.setSessionIdCookie(sessionIdCookie());
		// session全局超时时间, 单位:毫秒 ，30分钟 默认值为1800000
		webSessionManager.setGlobalSessionTimeout(1800000);
		//开启检测器，默认开启
		webSessionManager.setSessionIdUrlRewritingEnabled(true);
		// 检测间隔事件 时间为1小时
		webSessionManager.setSessionValidationInterval(3600000);
		// 设置监听器
		//webSessionManager.setSessionListeners();
		return webSessionManager;
	}

	@Bean
	public MemoryConstrainedCacheManager cacheManager() {
		return new MemoryConstrainedCacheManager();
	}

	@Bean(name = "securityManager")
	public SecurityManager securityManager() {
		DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
		//注入自定义myRealm
		securityManager.setRealm(myShiroRealm());
		//注入自定义cacheManager
		securityManager.setCacheManager(cacheManager());
		//注入记住我管理器
		securityManager.setRememberMeManager(rememberMeManager());
		//注入自定义sessionManager
		securityManager.setSessionManager(sessionManager());
		return securityManager;
	}

	//开启shiro aop注解支持，不开启的话权限验证就会失效
	@Bean
	public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
		DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator = new DefaultAdvisorAutoProxyCreator();
		defaultAdvisorAutoProxyCreator.setProxyTargetClass(true);
		return defaultAdvisorAutoProxyCreator;
	}

	@Bean
	public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(
			@Qualifier("securityManager") SecurityManager securityManager) {
		AuthorizationAttributeSourceAdvisor sourceAdvisor = new AuthorizationAttributeSourceAdvisor();
		sourceAdvisor.setSecurityManager(securityManager);
		return sourceAdvisor;
	}

	//配置异常处理，不配置的话没有权限后台报错，前台不会跳转到403页面
	@Bean(name = "simpleMappingExceptionResolver")
	public SimpleMappingExceptionResolver createSimpleMappingExceptionResolver() {
		SimpleMappingExceptionResolver simpleMappingExceptionResolver = new SimpleMappingExceptionResolver();
		Properties mappings = new Properties();
		mappings.setProperty("DatabaseException", "databaseError");//数据库异常处理
		mappings.setProperty("UnauthorizedException", "403");
		simpleMappingExceptionResolver.setExceptionMappings(mappings); // None by default
		simpleMappingExceptionResolver.setDefaultErrorView("403"); // No default
		simpleMappingExceptionResolver.setExceptionAttribute("ex"); // Default is "exception"
		return simpleMappingExceptionResolver;
	}
}