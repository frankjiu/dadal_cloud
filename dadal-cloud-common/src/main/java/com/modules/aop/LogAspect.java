/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.sysconfig   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年4月2日下午5:55:58
 * @version V1.0
 */

package com.modules.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * aop日志切面
 * 
 * @author: Frankjiu
 * @date: 2020年4月2日 下午5:55:58
 */
@Component
@Aspect
public class LogAspect {

	private final Logger logger = LoggerFactory.getLogger(this.getClass());

	@Pointcut("execution(* com.service.*.*(..))")
	public void pc1() {
		// aop日志切面定义
	}

	@Before(value = "pc1()")
	public void before(JoinPoint jp) {
		String name = jp.getSignature().getName();
		logger.info("======>{}方法开始执行...", name);
	}

	@After(value = "pc1()")
	public void after(JoinPoint jp) {
		String name = jp.getSignature().getName();
		logger.info("======>{}方法执行结束...", name);
	}

	@AfterReturning(value = "pc1()", returning = "result")
	public void afterReturning(JoinPoint jp, Object result) {
		String name = jp.getSignature().getName();
		logger.info("======>{}方法返回值:{}", name, result);
	}

}
