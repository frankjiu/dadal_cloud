package com.modules.thread.farmchild;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class DelThrd extends Thread {
	public void run() {
		while (true) {
			synchronized (Container.list) {
				//1.容器空了停止移除
				if (Container.list.size() == 0) {
					try {
						Container.list.wait();
					} catch (InterruptedException e) {
						log.info(e.getMessage(), e);
					}
				}
				//2.移除元素
				Container.list.remove("ab");
				log.info("------容器元素数量:" + Container.list.size());
				//3.唤醒
				Container.list.notify();
			}
			//4.模拟控制速度, 每隔2秒进行移除一个(删除慢,直到容器堆满)
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				log.info(e.getMessage(), e);
			}
		}
	}
}
