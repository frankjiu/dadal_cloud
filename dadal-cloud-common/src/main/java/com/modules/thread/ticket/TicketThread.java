package com.modules.thread.ticket;

import java.util.Queue;
import java.util.concurrent.ConcurrentLinkedQueue;

import lombok.extern.slf4j.Slf4j;

/**
 * @author: Frankjiu
 * @date: 2020年4月19日
 */
@Slf4j
public class TicketThread {
	// 创建队列容器
	static Queue<String> tickets = new ConcurrentLinkedQueue<>();
	// 向容器添加元素
	static {
		for (int i = 0; i < 15; i++)
			tickets.add(i + "号票");
	}

	// 重复从容器取元素测试
	public static void main(String[] args) {
		// 重复十次, 不会重复销售
		// ConcurrentLinkedQueue是一个并发队列,可以保证元素原子性,poll()表示从队列的头部获得一个数据
		// 队列底层使用CompareAndSet技术实现,不是加锁的实现,高并发下依然高效
		for (int i = 0; i < 10; i++) {
			new Thread(() -> {
				while (true) {
					String ticketNum = tickets.poll();
					if (ticketNum == null)
						break;
					else
						log.info("已销售" + ticketNum);
				}
			}).start();
		}
	}
}
