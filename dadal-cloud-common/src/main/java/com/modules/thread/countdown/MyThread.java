/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package zipfile.zippicfile   
 * @Description:    TODO 描述   
 * @author: Frankjiu
 * @date:   2019年12月1日 下午5:58:43   
 * @version V1.0
 */

package com.modules.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Frankjiu
 * @date: 2020年4月19日
 */
public class MyThread extends Thread {

	private int num;
	private CountDownLatch latch;
	private String threadName;

	public MyThread() {
		super();
	}

	public MyThread(int num, CountDownLatch latch, String threadName) {
		super();
		this.num = num;
		this.latch = latch;
		this.threadName = threadName;
	}

	@Override
	public void run() {
		String aString = "abcdefg";
		try {
			long start = System.currentTimeMillis();
			Thread.sleep(300);
			long end = System.currentTimeMillis();
			System.out.println(aString + " " + threadName + num + " it cost " + (end - start) + " ms");

		} catch (InterruptedException e) {
			e.printStackTrace();
		} finally {
			latch.countDown();
		}
	}

}
