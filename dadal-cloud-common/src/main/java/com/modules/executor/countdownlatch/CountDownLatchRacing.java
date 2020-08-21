/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor.countdownlatch   
 * @author: Frankjiu
 * @date: 2020年8月21日
 * @version: V1.0
 */

package com.modules.executor.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @Description: CountDownLatch Racing
 * @author: Frankjiu
 * @date: 2020年8月21日
 */
public class CountDownLatchRacing {

    private static final Integer THREAD_NUM = 2;

    public static void main(String[] args) {
        // 常量初始化
        CountDownLatch begin = new CountDownLatch(1);
        CountDownLatch end = new CountDownLatch(THREAD_NUM);

        // 调用子线程
        for (int i = 0; i < THREAD_NUM; i++) {
            new Thread(new Player(begin, end)).start();
        }

        try {
            System.out.println("the race begin");
            // 在当前主线程中, 唤醒子线程继续执行
            begin.countDown();
            // 阻塞当前主线程, 直到子线程调用N次后, 再继续执行主线程
            end.await();
            System.out.println("the race end");
        } catch (Exception e) {
            e.printStackTrace();
        }

    }
}

/**
 * 选手
 */
class Player implements Runnable {
    private CountDownLatch begin;
    private CountDownLatch end;

    Player(CountDownLatch begin, CountDownLatch end) {
        this.begin = begin;
        this.end = end;
    }

    public void run() {
        try {
            // 阻塞当前子线程的执行
            begin.await();

            System.out.println(Thread.currentThread().getName() + " arrived !");
            // 调用子线程计数
            end.countDown();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}