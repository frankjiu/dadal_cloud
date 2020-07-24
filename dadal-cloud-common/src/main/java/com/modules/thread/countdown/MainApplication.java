/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package zipfile.zippicfile   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年12月1日 下午6:02:07   
 * @version V1.0
 */

package com.modules.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Frankjiu
 * @date: 2020年4月19日
 */
public class MainApplication {

	public static void main(String[] args) {
		int k = 6;
		CountDownLatch latch = new CountDownLatch(k);
		for (int i = 0; i < k; i++) {
			MyThread thread = new MyThread(i, latch, "子线程");
			thread.start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

		System.out.println("执行完毕!");

	}
}
