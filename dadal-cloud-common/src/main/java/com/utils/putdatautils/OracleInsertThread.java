/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package com.utils.putdatautils   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2020年2月13日下午8:02:24
 * @version V1.0
 */

package com.utils.putdatautils;

/**
 * @author: Frankjiu
 * @date: 2020年2月13日 下午8:02:24
 */

public class OracleInsertThread {

	public static void main(String[] args) {

		TestDataOracle td1 = new TestDataOracle("Thread_A", 3000000, 1000000, 4000000);
		TestDataOracle td2 = new TestDataOracle("Thread_B", 3000000, 4000000, 7000000);
		TestDataOracle td3 = new TestDataOracle("Thread_C", 3000000, 7000000, 10000000);

		(new Thread(td1)).start();
		(new Thread(td2)).start();
		(new Thread(td3)).start();
	}
}
