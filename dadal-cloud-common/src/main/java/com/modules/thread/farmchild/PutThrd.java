package com.modules.thread.farmchild;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PutThrd extends Thread {
	public void run() {
		while (true) {
			synchronized (Container.list) {
				//1.容器满了停止添加
				if (Container.list.size() >= 5) {
					try {
						Container.list.wait();
					} catch (InterruptedException e) {
						log.info(e.getMessage(), e);
					}
				}
				//2.添加元素
				Container.list.add("ab");
				log.info("++++++容器元素数量:" + Container.list.size());
				//3.唤醒
				Container.list.notify();
			}
			//4.模拟控制速度, 每隔1.5秒添加一个
			try {
				Thread.sleep(1500);
			} catch (InterruptedException e) {
				log.info(e.getMessage(), e);
			}
		}
	}
}
