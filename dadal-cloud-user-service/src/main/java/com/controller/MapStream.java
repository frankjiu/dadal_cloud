/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.controller   
 * @author: Frankjiu
 * @date: 2020年8月7日
 * @version: V1.0
 */

package com.controller;

import java.math.BigDecimal;
import java.util.AbstractMap;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.lang.reflect.FieldUtils;

/**
 * @Description: TODO
 * @author: Frankjiu
 * @date: 2020年8月7日
 */
public class MapStream {

    public static void main(String[] args) throws IllegalAccessException {

        List<Student> list = new ArrayList<>();

        Student student1 = new Student();
        student1.setName("张三");
        student1.setAge(23);
        Student student2 = new Student();
        student2.setName("李四");
        student2.setAge(24);
        Student student3 = new Student();
        student3.setName("王五");
        student3.setAge(25);
        Student student4 = new Student();
        student4.setName("李四");
        student4.setAge(20);
        list.add(student1);
        list.add(student2);
        list.add(student3);
        list.add(student4);

        /*Stream<Integer> map = list.stream().map(e -> {
            System.out.println(e);
            return e.getAge();
        });*/

        //[张三=23, 李四=24, 王五=25]
        //{李四=[24], 张三=[23], 王五=[25]}
        //[李四=[24], 张三=[23], 王五=[25]]
        //Set<Entry<String, List<BigDecimal>>> entrySet = 
        /*李四
                            张三
                            王五
         [李四=44, 张三=23, 王五=25]*/

        //[李四, 王五, 张三]

        //List<String> listNames = 

        List<String> listNameTop2 = list.stream().map(e -> {
            try {
                BigDecimal num1 = new BigDecimal(FieldUtils.readField(e, "age", true).toString());
                Object key = FieldUtils.readField(e, "name", true);
                return new HashMap.SimpleEntry<>(key == null ? "" : key.toString(), num1);
            } catch (Exception e1) {
                e1.printStackTrace();
                return null;
            }
        }).collect(Collectors.groupingBy(AbstractMap.SimpleEntry::getKey,
                Collectors.mapping(AbstractMap.SimpleEntry::getValue, Collectors.toList()))).entrySet().stream().map(x -> {
                    BigDecimal num1 = BigDecimal.ZERO;
                    System.out.println(x.getKey());
                    for (BigDecimal value : x.getValue()) {
                        num1 = num1.add(value);
                    }
                    return new HashMap.SimpleEntry<>(x.getKey(), num1); //[李四, 王五, 张三]  //[李四, 王五]
                }).sorted((x, y) -> -x.getValue().compareTo(y.getValue())).limit(2).map(Map.Entry::getKey).collect(Collectors.toList());
        //[Student(name=张三, age=23, newOrder=0003), 
        //Student(name=李四, age=24, newOrder=0001), 
        //Student(name=王五, age=25, newOrder=0002),
        //Student(name=李四, age=20, newOrder=0001)]
        System.out.println(listNameTop2);
        System.out.println("........");
        List<Student> collect = list.parallelStream().filter(x -> {
            try {
                String fieldValue = FieldUtils.readField(x, "name", true).toString();
                return listNameTop2.contains(fieldValue);
            } catch (Exception e) {
                return false;
            }
        }).map(s -> {
            try {
                Student student = new Student();
                BeanUtils.copyProperties(student, s);
                Object fieldValue = FieldUtils.readField(s, "name", true);
                int index = listNameTop2.indexOf(fieldValue) + 1;
                String newIndex = "000" + index;
                FieldUtils.writeField(student, "newOrder", newIndex, true);
                return student;
            } catch (Exception e) {
                return null;
            }
        }).collect(Collectors.toList());

        //System.out.println(listNames);
        System.out.println(">>>" + collect);
        // Student(name=李四, age=24, newOrder=0001), 
        // Student(name=王五, age=25, newOrder=0002), 
        // Student(name=李四, age=20, newOrder=0001)
    }

}
