/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.test   
 * @author: Frankjiu
 * @date: 2020年9月23日
 * @version: V1.0
 */

package com.test1;

import org.junit.Test;

/**
 * @Description: 循环不尽, 永远拿 stb截取尾部一个and后 赋值给 sqlBlock
 * @author: Frankjiu
 * @date: 2020年9月23日
 */

public class WhileTest {

    private static final String AND = "and";

    @Test
    public void testWhile() {
        StringBuilder stb = new StringBuilder(" and a and b and c and d and and and ");
        String sqlBlock = stb.toString();

        if (sqlBlock.trim().startsWith(AND)) {
            sqlBlock = sqlBlock.replaceFirst(AND, "");
        }

        /** stb.toString()!!!!!!!!!! */
        while (sqlBlock.trim().endsWith(AND)) {
            sqlBlock = new StringBuffer(stb.toString().trim()).replace(sqlBlock.trim().length() - 3, sqlBlock.trim().length(), "")
                    .toString();
        }

        System.out.println(sqlBlock);
    }

}
