package com.modules.thread.countdown;

import java.util.concurrent.CountDownLatch;

/**
 * @author: Frankjiu
 * @date: 2020年4月19日
 */
public class CountDownLatchTest {

	public static void main(String[] args) {
		CountDownLatch latch = new CountDownLatch(3);
		long start = System.currentTimeMillis();
		for (int i = 0; i < 3; i++) {
			new Thread(new SubRunnable(i, latch)).start();
		}
		try {
			latch.await();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(System.currentTimeMillis() - start);

		System.out.println("Main finished");
	}

	static class SubRunnable implements Runnable {
		private int id = -1;
		private CountDownLatch latch;

		SubRunnable(int id, CountDownLatch latch) {
			this.id = id;
			this.latch = latch;
		}

		public void run() {
			try {
				Thread.sleep(3000);
				System.out.println(String.format("Sub Thread %d finished", id));
			} catch (InterruptedException e) {
				e.printStackTrace();
			} finally {
				latch.countDown();
			}
		}
	}
}
