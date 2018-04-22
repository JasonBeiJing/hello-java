package com.hello.java.concurrent;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class QPSControl {
	final static int MAX_QPS = 10;
	final static Semaphore semaphore = new Semaphore(MAX_QPS);

	final static ExecutorService pool = Executors.newFixedThreadPool(100);

	public static void main(String... args) throws Exception {
		Executors.newScheduledThreadPool(1).scheduleAtFixedRate(new Runnable() {
			@Override
			public void run() {
				int availablePermits = semaphore.availablePermits();
				System.err.println("=======> " + availablePermits);
				if(availablePermits < MAX_QPS) {					
					semaphore.release(MAX_QPS-availablePermits);
				}
			}
		}, 1, 1, TimeUnit.SECONDS);

		// lots of concurrent calls:100 * 1000
		for (int i = 10; i > 0; i--) {
			final int x = i;
			pool.submit(new Runnable() {
				@Override
				public void run() {
					for (int j = 10; j > 0; j--) {
						semaphore.acquireUninterruptibly(1);
						callRCP(x, j);
					}
				}
			});
		}
		pool.shutdown();
		pool.awaitTermination(1, TimeUnit.HOURS);
		System.out.println("DONE");
	}

	private static void callRCP(int i, int j) {
		System.out.println(Thread.currentThread().getId() + " ===[" + i +"-->"+ j + "]");
	}
}
