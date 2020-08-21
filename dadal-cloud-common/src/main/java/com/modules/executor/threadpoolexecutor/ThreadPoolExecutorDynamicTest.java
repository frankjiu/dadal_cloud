/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor   
 * @author: Frankjiu
 * @date: 2020年8月20日
 * @version: V1.0
 */

package com.modules.executor.threadpoolexecutor;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @Description: 根据业务并发量动态调整线程池大小测试
 * @author: Frankjiu
 * @date: 2020年8月20日
 */
public class ThreadPoolExecutorDynamicTest {
    public static void main(String[] args) throws InterruptedException {
        // Init the parameters: corePoolSize, maximumPoolSize, keepAliveTime, timeUnit, workQueue
        ThreadPoolExecutor executor = new ThreadPoolExecutor(3, 11, 30, TimeUnit.SECONDS, new LinkedBlockingQueue<>(10));
        //new ThreadPoolExecutor(corePoolSize, maximumPoolSize, keepAliveTime, unit, workQueue, threadFactory, handler)

        // 模拟业务数据的并发量
        int count = 0;

        // 保持运行状态
        while (true) {
            Thread.sleep(1000L);
            // 循环开启任务扔给线程池
            for (int i = 0; i < 5; i++) {
                executor.execute(() -> {
                    System.out.println("------------corePoolSize:\t" + executor.getCorePoolSize() + "\tactiveThreadSize:\t"
                            + executor.getActiveCount() + "\tmaximumPoolSize:\t" + executor.getMaximumPoolSize());
                });
            }

            // 业务数据并发量动态调整
            count++;

            if (count == 10) {
                executor.setCorePoolSize(2);
                executor.setMaximumPoolSize(8);
                System.out.println("-------------------动态修改线程池大小为2, 8---------------------");
            }

            if (count == 50) {
                executor.setCorePoolSize(5);
                executor.setMaximumPoolSize(17);
                System.out.println("-------------------动态修改线程池大小为5, 17---------------------");
            }

            if (count == 70) {
                executor.shutdown();
                System.out.println("=======================关闭线程池======================");
                break;
            }
        }
        Thread.currentThread().join();
    }
}