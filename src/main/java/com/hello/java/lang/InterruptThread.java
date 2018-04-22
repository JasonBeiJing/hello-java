package com.hello.java.lang;

import java.util.concurrent.TimeUnit;

public class InterruptThread {

	public static void main(String[] args) throws InterruptedException {
		interruptRunningThread();
		interruptSleepingThread();
	}
	
	private static void interruptRunningThread() throws InterruptedException {
		Thread thread = new Thread() {
			public void run() {
				System.out.println("start thread......");
				while (true) {
					System.err.println();
					if(this.isInterrupted()) {
						break;
					}
				}
				System.out.println(this.hashCode() + " haha，interrupted ：" + this.isInterrupted());
			}
		};
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		thread.interrupt(); //a flag to tell JVM, please help to stop this thread ...
		System.out.println(thread.hashCode() + " over：" + thread.isInterrupted());
	}
	
	private static void interruptSleepingThread() throws InterruptedException {
		Thread thread = new Thread() {
			public void run() {
				System.err.println("start thread......");
				try {
					TimeUnit.SECONDS.sleep(10);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.err.println(this.hashCode() + " haha，interrupted ：" + this.isInterrupted());
			}
		};
		thread.start();
		TimeUnit.SECONDS.sleep(1);
		thread.interrupt(); //a flag to tell JVM, please help to stop this thread ...
		System.err.println(thread.hashCode() + " over：" + thread.isInterrupted());
	}
}
