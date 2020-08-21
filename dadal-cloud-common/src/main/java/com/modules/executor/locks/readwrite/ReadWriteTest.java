/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor.locks.readwrite   
 * @author: Frankjiu
 * @date: 2020年8月21日
 * @version: V1.0
 */

package com.modules.executor.locks.readwrite;

/**
 * @Description: 读写互斥
 * @author: Frankjiu
 * @date: 2020年8月21日
 */
public class ReadWriteTest {

    public static void main(String[] args) {
        final RWTask task = new RWTask();
        Thread readThread = new Thread(new Runnable() {
            @Override
            public void run() {
                task.read();
            }
        }, "read");

        Thread writeThread = new Thread(new Runnable() {
            @Override
            public void run() {
                task.write();
            }
        }, "write");

        try {
            readThread.start();
            Thread.sleep(2000);
            writeThread.start();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}