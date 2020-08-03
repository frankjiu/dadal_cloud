/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年7月31日
 * @version: V1.0
 */

package com.controller;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年7月31日
 */

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.stream.Collectors;

public class Tests {

	public static void main(String[] args) {

		List<User> userList = new ArrayList<User>();
		userList.add(new User("杨胜军", 23, '男'));
		userList.add(new User("杨胜军", 21, '男'));
		userList.add(new User("杨胜军", 22, '女'));
		userList.add(new User("杨胜军", 24, '女'));
		userList.add(new User("黄海明", 23, '男'));
		userList.add(new User("黄海明", 22, '男'));
		userList.add(new User("黄海明", 22, '女'));
		userList.add(new User("黄海明", 24, '女'));
		userList.add(new User("赵忠有", 23, '男'));
		userList.add(new User("赵忠有", 21, '男'));
		userList.add(new User("赵忠有", 22, '女'));
		userList.add(new User("赵忠有", 21, '女'));

		Map<String, List<User>> boxBarcodeMap = userList.stream().collect(Collectors.groupingBy(User::getName));

		Set<Entry<String, List<User>>> entrySet = boxBarcodeMap.entrySet();

		for (Entry<String, List<User>> childMap : entrySet) {
			System.out.print("年龄:" + childMap.getKey());

			List<User> users = childMap.getValue();

			for (User user : users) {
				System.out.print(" \t 姓名:" + user.getName());
			}

			System.out.print("\n");
		}

		// -1为倒序排列, 1为正序排列.
		List<String> list = entrySet.stream().sorted((new Comparator<Entry<String, List<User>>>() {
			@Override
			public int compare(Entry<String, List<User>> o1, Entry<String, List<User>> o2) {
				if ("Direct".equals(o1.getKey())) {
					return -1;
				} else if ("Single".equals(o1.getKey()) && ("Double".equals(o2.getKey()))) {
					return -1;
				} else if ("Double".equals(o1.getKey())
						&& !("Direct".equals(o2.getKey()) || "Single".equals(o2.getKey()) || "Double".equals(o2.getKey()))) {
					return -1;
				}
				return -1;
			}
		})).map(Map.Entry::getKey)/*.distinct()*/.collect(Collectors.toList());

		//Map<String, Long> map = new HashMap<>();
		for (String i : list) {
			//Integer age = user.getAge();
			System.out.println(i);
		}

	}

	public static class User {

		public User(String name, int age, char sex) {
			super();
			this.name = name;
			this.age = age;
			this.sex = sex;
		}

		private String name;
		private int age;
		private char sex;

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public int getAge() {
			return age;
		}

		public void setAge(int age) {
			this.age = age;
		}

		public char getSex() {
			return sex;
		}

		public void setSex(char sex) {
			this.sex = sex;
		}
	}
}