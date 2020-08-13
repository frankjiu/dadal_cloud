package com.controller;

import java.time.LocalDate;
import java.util.Objects;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class AAA {

    private static String str = "hello";

    @Autowired
    private BBB b;

    public AAA() {
        System.out.println("此时b还未被注入: b = " + b);
    }

    @PostConstruct
    private void init() {
        System.out.println("@PostConstruct将在依赖注入完成后被自动调用: b = " + b);
    }

    public void testing() {
        System.out.println("测试");
    }

    public static void main(String[] args) {
        AAA aaa = new AAA();

        //On UNIX systems, it returns "\n"; on Microsoft Windows systems it returns "\r\n".
        String lineSeparator = System.lineSeparator();
        log.info(lineSeparator);

        String s = AAA.str;
        System.out.println(s);
        s.equals(aaa);
        boolean equals = Objects.equals(s, aaa);

        LocalDate now = LocalDate.now();
        LocalDate plusDays = now.plusDays(3);

        System.out.println(plusDays);

        aaa.testing();
    }

}
