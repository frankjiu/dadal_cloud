/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年7月31日
 * @version: V1.0
 */

package com.controller;

import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.junit.Test;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月31日
 */

public class InsertToListFirst {

	@Test
	public void test() {

		List<String> list = new ArrayList<>();
		list.add("AZ");
		list.add("CZ");
		list.add("SZ");

		if (list.contains("CZ")) {
			list.remove("CZ");
			list.add(0, "CZ");
		}

		String str = StringUtils.join(list.toArray(), ",");
		System.out.println(str);
	}

}
