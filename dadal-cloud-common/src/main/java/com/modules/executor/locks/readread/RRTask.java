/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor.locks   
 * @author: Frankjiu
 * @date: 2020年8月21日
 * @version: V1.0
 */

package com.modules.executor.locks.readread;

import java.util.concurrent.locks.ReentrantReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock.ReadLock;

/**
 * @Description: 读读任务类
 * @author: Frankjiu
 * @date: 2020年8月21日
 */

public class RRTask {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        ReadLock readLock = lock.readLock();
        try {
            readLock.lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(5000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            readLock.unlock();
        }
    }
}