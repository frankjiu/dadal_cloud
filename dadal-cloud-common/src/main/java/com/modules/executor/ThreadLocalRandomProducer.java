/**
 * All rights Reserved, Designed By www.xcompany.com  
 * @Package: com.modules.executor   
 * @author: Frankjiu
 * @date: 2020年8月21日
 * @version: V1.0
 */

package com.modules.executor;

/**
 * @Description: 在并发场景下使用ThreadLocalRandom而不是共享的Random对象通常会使得开销和竞争更小
 * @author: Frankjiu
 * @date: 2020年8月21日
 */
import java.util.concurrent.ThreadLocalRandom;

public class ThreadLocalRandomProducer {

    public static void main(String[] args) {
        for (int i = 0; i < 10; i++) {
            new Company().start();
        }
    }

}

class Company extends Thread {
    @Override
    public void run() {
        System.out.println(getName() + ": " + ThreadLocalRandom.current().nextInt(10, 20));
    }
}