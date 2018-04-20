package com.hello.java;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;

public class ConcurrentSemaphore {
	private static final ExecutorService es = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		Semaphore semaphore = new Semaphore(3);

		for (int i = 0; i < 20; i++) {
			es.submit(new Task(semaphore, i));
		}
		es.shutdown();
	}

	static class Task implements Runnable {
		private final Semaphore semaphore;
		private final int i;

		public Task(Semaphore semaphore, int i) {
			this.semaphore = semaphore;
			this.i = i;
		}

		@Override
		public void run() {
			try {
				semaphore.acquire(); // acquire a permit
				System.err.println("------>" + i);
				TimeUnit.SECONDS.sleep(2);
			} catch (Exception e) {
			} finally {
				semaphore.release(); // done, release the permit
			}
		}
	}
}
