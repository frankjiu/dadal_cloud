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
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * @Description: ScheduledThreadPoolExecutor 使用测试
 * @author: Frankjiu
 * @date: 2020年8月20日
 */
public class ScheduledThreadPoolExecutorTestOne {

    public static void main(String[] args) {
        ScheduledExecutorService scheduledThreadPool = Executors.newScheduledThreadPool(5);
        try {
            //schedule to run after sometime
            System.out.println("Current Time = " + getNowDate());
            for (int i = 0; i < 3; i++) {
                Thread.sleep(1000);
                WorkerThread worker = new WorkerThread();
                //延迟5秒后执行
                scheduledThreadPool.schedule(worker, 10, TimeUnit.SECONDS);
            }
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        scheduledThreadPool.shutdown();
        while (!scheduledThreadPool.isTerminated()) {
            //wait for all tasks to finish
        }
        System.out.println("Finished all threads");
    }

    /**
     * 获取现在时间
     */
    public static String getNowDate() {
        Date currentTime = new Date();
        SimpleDateFormat formatter;
        formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(currentTime);
    }

}