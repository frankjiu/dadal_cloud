/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor   
 * @author: Frankjiu
 * @date: 2020年8月20日
 * @version: V1.0
 */

package com.modules.executor.threadpoolexecutor;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 创建工作线程用于测试
 * @author: Frankjiu
 * @date: 2020年8月20日
 */
public class WorkerThread implements Runnable {
    @Override
    public void run() {
        System.out.println(Thread.currentThread().getName() + " Start. Time = " + getNowDate());
        threadSleep();
        System.out.println(Thread.currentThread().getName() + " End. Time = " + getNowDate());
    }

    /**
     * 睡3秒
     */
    public void threadSleep() {
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取现在时间
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String ctime = formatter.format(currentTime);
        return ctime;
    }
}
