/**   
 * @author: Frankjiu
 * @date: 2020年6月7日
 */
package com.util;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 日志的注解类
 * @author: Frankjiu
 * @date: 2020年6月7日
 */
@Target({ ElementType.PARAMETER, ElementType.METHOD })
@Documented
@Retention(RetentionPolicy.RUNTIME)
public @interface SysLog {
	/**
	 * 操作类型
	 * 
	 * @return String
	 */
	public String operationType() default "";

	/**
	 * 操作模块
	 * 
	 * @return String
	 */
	public String operationModule() default "";

	/**
	 * 操作备注
	 * 
	 * @return String
	 */
	public String remark() default "";

}
