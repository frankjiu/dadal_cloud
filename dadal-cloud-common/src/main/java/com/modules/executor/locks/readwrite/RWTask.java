/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor.locks   
 * @author: Frankjiu
 * @date: 2020年8月21日
 * @version: V1.0
 */

package com.modules.executor.locks.readwrite;

import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * @Description: 读写任务类
 * @author: Frankjiu
 * @date: 2020年8月21日
 */
public class RWTask {

    private ReentrantReadWriteLock lock = new ReentrantReadWriteLock();

    public void read() {
        try {
            lock.readLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.readLock().unlock();
        }
    }

    public void write() {
        try {
            lock.writeLock().lock();
            System.out.println(Thread.currentThread().getName() + " start");
            Thread.sleep(3000);
            System.out.println(Thread.currentThread().getName() + " end");
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.writeLock().unlock();
        }
    }
}
