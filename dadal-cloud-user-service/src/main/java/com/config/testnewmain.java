/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.config   
 * @author: Frankjiu
 * @date: 2020年7月3日
 * @version: V1.0
 */

package com.config;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月3日
 */

public class testnewmain {

	public static void main(String[] args) {
		List<User> list = new ArrayList<>();
		list.add(new User(21L, "张三"));
		list.add(new User(25L, "李四"));
		list.add(new User(22L, "王五"));
		list.add(new User(19L, "赵柳"));
		list.add(new User(32L, "王5"));
		list.add(new User(29L, "王6"));
		list.add(new User(21L, "王7"));

		// 对象根据年龄属性升序排序
		List<User> newList = list.stream().sorted(Comparator.comparing(User::getAge)).collect(Collectors.toList());
		List<User> list2 = newList.stream().filter(e -> e.getAge() != null).collect(Collectors.toList());

		StringBuilder stb = new StringBuilder();
		System.out.println("//////");
		System.out.println(stb.toString());

		long time = new Date().getTime();
		System.out.println(time);//1594028423523

		String convertToDateString = StringUtil.convertToDateTimeString(time);
		System.out.println(convertToDateString);
		// list遍历
		//newList.forEach(System.out::println);
		list2.forEach(System.out::println);

		String obDepTime = "07:10";

		String[] arr = obDepTime.split(":");
		String b = arr[0].concat(arr[1]);

		String a = obDepTime.substring(0, 2).concat(obDepTime.substring(3, 5));
		System.out.println(b);

		String cabin = "Economy";
		String cabinClass = cabin == "First" ? "F" : (cabin == "Business" ? "C" : (cabin == "Economy" ? "Y" : ""));
		System.out.println(cabinClass);

		Double s = null;
		System.out.println(s);

		Integer stayDays = null;
		System.out.println(stayDays);

	}
}
