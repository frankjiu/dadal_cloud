/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor.locks   
 * @author: Frankjiu
 * @date: 2020年8月21日
 * @version: V1.0
 */

package com.modules.executor.locks.readread;

/**
 * @Description: 读读共享
 * @author: Frankjiu
 * @date: 2020年8月21日
 */
public class ReadReadTest {

    public static void main(String[] args) {
        final RRTask task = new RRTask();
        Thread readThreadOne = new Thread(new Runnable() {
            @Override
            public void run() {
                task.read();
            }
        }, "read1");

        Thread readThreadTwo = new Thread(new Runnable() {
            @Override
            public void run() {
                task.read();
            }
        }, "read2");

        // start thread
        readThreadOne.start();
        readThreadTwo.start();
    }

}