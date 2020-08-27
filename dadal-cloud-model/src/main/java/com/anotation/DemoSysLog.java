/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @author: Frankjiu
 * @date: 2020年6月3日
 * @version: V1.0
 */

package com.anotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @Description: 系统日志注解
 * @author: Frankjiu
 * @date: 2020年6月3日
 */
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface DemoSysLog {

    /**
     * 操作类型
     */
    public String operationType() default "";

    /**
     * 操作模块
     */
    public String operationModule() default "";

    /**
     * 描述
     */
    public String description() default "";

    /**
     * 备注
     */
    public String remark() default "";

}