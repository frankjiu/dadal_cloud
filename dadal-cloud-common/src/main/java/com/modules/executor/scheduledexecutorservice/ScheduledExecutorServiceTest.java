/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor   
 * @author: Frankjiu
 * @date: 2020年8月20日
 * @version: V1.0
 */

package com.modules.executor.scheduledexecutorservice;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

import org.junit.Test;

import lombok.extern.slf4j.Slf4j;

/**
 * @Description: ScheduledExecutorService任务调度接口测试
 * @author: Frankjiu
 * @date: 2020年8月20日
 */
@Slf4j
public class ScheduledExecutorServiceTest {

    /**
     * 时间格式化
     */
    private String format(long currentTimeMillis) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return formatter.format(new Date(currentTimeMillis));
    }

    /**
     * 1.schedule Runnable: 该方法用于带延迟时间的调度，只执行一次。调度之后可通过Future.get()阻塞直至任务执行完毕;
     * 特点: 延迟执行时间为1秒，任务执行3秒，任务只执行一次，同时通过Future.get()阻塞直至任务执行完毕.
     */
    //@Test
    public void test_schedule4Runnable() throws Exception {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<?> future = service.schedule(() -> {
            System.out.println("task start time: " + format(System.currentTimeMillis()));
            try {
                Thread.sleep(3000L); // 任务执行3秒
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + format(System.currentTimeMillis()));
        }, 2000, TimeUnit.MILLISECONDS); // 任务延迟2秒
        System.out.println("定时计划开始时间: " + format(System.currentTimeMillis()));
        System.out.println("Runnable future's result is: " + future.get() + ", and time is: " + format(System.currentTimeMillis()));
    }

    /**
     * 2.schedule Callable: 在上述schedule Runnable的基础上,
     * future.get()能拿到Callable返回的真实结果.
     */
    //@Test
    public void test_schedule4Callable() throws Exception {
        ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        ScheduledFuture<String> future = service.schedule(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + format(System.currentTimeMillis()));
            return "success";
        }, 2000, TimeUnit.MILLISECONDS);
        System.out.println("schedule start time: " + format(System.currentTimeMillis()));
        System.out.println("Callable future's result is: " + future.get() + ", and time is: " + format(System.currentTimeMillis()));
    }

    /**
     * 3.scheduleAtFixedRate: 该方法用于固定频率地对一个任务循环执行; 特点:
     * 任务初始延迟1秒，任务执行3秒，任务执行间隔为1秒.
     */
    //@Test
    public void test_scheduleAtFixedRate() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleAtFixedRate(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + format(System.currentTimeMillis()));
        }, 1000L, 1000L, TimeUnit.MILLISECONDS);

        System.out.println("schedule start time: " + format(System.currentTimeMillis()));
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info(e.getMessage(), e);
            }
        }
    }

    /**
     * 4.scheduleWithFixedDelay: 该方法用于固定延迟地对一个任务循环执行; 特点:
     * 任务初始延迟1秒，任务执行3秒，任务固定延迟为2秒.
     */
    @Test
    public void test_scheduleWithFixedDelay() {
        ScheduledExecutorService service = Executors.newScheduledThreadPool(5);
        service.scheduleWithFixedDelay(() -> {
            try {
                Thread.sleep(3000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("task finish time: " + format(System.currentTimeMillis()));
        }, 1000L, 2000L, TimeUnit.MILLISECONDS);

        System.out.println("schedule start time: " + format(System.currentTimeMillis()));
        while (true) {
            try {
                Thread.sleep(1);
            } catch (InterruptedException e) {
                log.info(e.getMessage(), e);
            }
        }
    }

}
