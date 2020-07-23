/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.aop   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月29日下午6:51:48
 * @version V1.0
 */

package com.modules.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

/**
 * @author: Frankjiu
 * @date: 2020年2月29日 下午6:51:48
 */
@Component
@Aspect
/**
 * 切面(切点和通知的组合)
 * 
 * @author: Frankjiu
 * @date: 2020年2月29日 下午7:02:39
 */
public class MyAspect {

	/**
	 * 切点(满足条件的目标方法) 注:所有方法统称为连接点,故切点是连接点的子集
	 */
	@Pointcut("execution(* com.aop.service..*.*(..))")
	public void myPointcut123() {
	}

	/**
	 * 通知
	 */
	@Before("myPointcut123()") // 执行到某个切点(一个或多个方法)之前
	public void advice(JoinPoint jp) {
		System.out.println("..." + System.currentTimeMillis());// 代理逻辑
	}

}
