/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.retention   
 * @author: Frankjiu
 * @date: 2020年8月3日
 * @version: V1.0
 */

package com.retention;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

/**
 * @Description: 实现自定义注解
 * @author: Frankjiu
 * @date: 2020年8月3日
 */
public class IdentityCardNumberValidator implements ConstraintValidator<IdentityCardNumber, Object> {

	@Override
	public void initialize(IdentityCardNumber identityCardNumber) {
	}

	@Override
	public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
		return IdcardUtils.validateIdCard18(o.toString());
	}
}