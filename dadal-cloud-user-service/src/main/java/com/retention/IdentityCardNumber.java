/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.retention   
 * @author: Frankjiu
 * @date: 2020年8月3日
 * @version: V1.0
 */

package com.retention;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.validation.Constraint;
import javax.validation.Payload;

/**
 * @Description: 自定义注解
 * @author: Frankjiu
 * @date: 2020年8月3日
 */
@Documented
@Target({ ElementType.PARAMETER, ElementType.FIELD })
@Retention(RetentionPolicy.RUNTIME)
@Constraint(validatedBy = IdentityCardNumberValidator.class)
public @interface IdentityCardNumber {

	String message() default "身份证号码不合法";

	Class<?>[] groups() default {};

	Class<? extends Payload>[] payload() default {};
}