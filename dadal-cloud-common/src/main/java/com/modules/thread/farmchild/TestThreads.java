package com.modules.thread.farmchild;

/**
 * 线程通信测试
 */
public class TestThreads {
	public static void main(String[] args) {
		new PutThrd().start();
		new DelThrd().start();
	}
}
