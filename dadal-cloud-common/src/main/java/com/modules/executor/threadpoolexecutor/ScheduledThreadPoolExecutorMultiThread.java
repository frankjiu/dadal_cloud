/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor   
 * @author: Frankjiu
 * @date: 2020年8月20日
 * @version: V1.0
 */

package com.modules.executor.threadpoolexecutor;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: ScheduledThreadPoolExecutor 多线程使用测试
 * @author: Frankjiu
 * @date: 2020年8月20日
 */

public class ScheduledThreadPoolExecutorMultiThread {
    private static Integer count = 1;
    MyTimereTask myTimereTask = new MyTimereTask();
    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    ScheduledExecutorService scheduled = Executors.newScheduledThreadPool(2);

    public void start() {
        try {
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            scheduled.scheduleWithFixedDelay(myTimereTask, 0, 1, TimeUnit.SECONDS);
            while (!scheduled.isTerminated()) {
                lock.readLock().lock();
                if (count > 20) {
                    scheduled.shutdown();
                }
                lock.readLock().unlock();

            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("Finished all threads");
    }

    private class MyTimereTask implements Runnable {
        @Override
        public void run() {
            lock.writeLock().lock();
            System.out.println("第 " + count + " 次执行任务,count=" + count);
            count++;
            lock.writeLock().unlock();
        }

    }
}