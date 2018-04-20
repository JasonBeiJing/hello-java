package com.hello.java.lang;

import java.util.concurrent.TimeUnit;

public class ThreadJoin {

	static class Task implements Runnable{
		private final Thread afterThred;
		
		public Task(Thread afterThred) {
			super();
			this.afterThred = afterThred;
		}
		@Override
		public void run() {
			try {
				if(afterThred!=null) {					
					afterThred.join(); //afterThred 挡住当前线程，afterThred在当前线程之前优先执行
				}
				TimeUnit.SECONDS.sleep(1);
			} catch (Exception e) {
			}
			System.out.println("--->"+Thread.currentThread().getName()+"结束 ----");
		}
	}

	public static void main(String[] args) throws InterruptedException {
		Thread t1 = new Thread(new Task(null), "线程1");
		Thread t2 = new Thread(new Task(t1), "线程2");
		Thread t3 = new Thread(new Task(t2), "线程3");
		Thread t4 = new Thread(new Task(t3), "线程4");
		
		//打乱线程开始顺序
		t2.start();
		t4.start();
		t1.start();
		t3.start();
		
		System.err.println("===主线程迅速结束===");
	}

	//执行结果
	//===主线程迅速结束===
	//--->线程1结束 ----
	//--->线程2结束 ----
	//--->线程3结束 ----
	//--->线程4结束 ----
}
