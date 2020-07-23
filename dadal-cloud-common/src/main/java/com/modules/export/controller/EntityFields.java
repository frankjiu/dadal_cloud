/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年7月17日
 * @version: V1.0
 */

package com.modules.export.controller;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.beanutils.PropertyUtilsBean;
import org.junit.Test;

import com.modules.export.entity.Partner;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月17日
 */

public class EntityFields {

	public static void testGetFields(Object obj) {
		try {
			PropertyUtilsBean propertyUtilsBean = new PropertyUtilsBean();
			PropertyDescriptor[] descriptors = propertyUtilsBean.getPropertyDescriptors(obj);
			for (int i = 0; i < descriptors.length; i++) {
				String name = descriptors[i].getName();
				if (!"class".equals(name)) {
					System.out.println(name + ":" + propertyUtilsBean.getNestedProperty(obj, name));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Test
	public <E> void testing() throws NoSuchFieldException, SecurityException {

		//EmployeeReportResult
		Partner employeeReportResult1 = new Partner();
		employeeReportResult1.setUserId(111);
		employeeReportResult1.setUsername("zhang");
		employeeReportResult1.setMobile("13328663545");
		Partner employeeReportResult2 = new Partner();
		employeeReportResult2.setUserId(222);
		employeeReportResult2.setUsername("wang");
		employeeReportResult2.setMobile("19928663545");

		List<Partner> list = new ArrayList<>();
		list.add(employeeReportResult1);
		list.add(employeeReportResult2);

		//testGetFields(employeeReportResult);
		/*E e = (E) new EmployeeReportResult();
		Field[] fields = e.getClass().getDeclaredFields();
		for (Field field : fields) {
			if (!field.isAccessible()) {
				field.setAccessible(true);
			}
			System.out.println(field);
		}*/

		Partner e; //实体类  
		Field[] fields; //属性数组  
		Field field; //属性  
		Object value; //属性值  
		for (int i = 0; i < list.size(); i++) {
			e = list.get(i);
			fields = e.getClass().getDeclaredFields();
			for (Field field2 : fields) {
				System.out.println(field2);
			}
		}

	}

}
