package com.hello.java.util;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class UnsafeHashMap {

	private final static int COUNT = 1000;
	
	private final static CountDownLatch cdl = new CountDownLatch(COUNT);
	private final static ExecutorService es = Executors.newCachedThreadPool();
	
	public static void main(String[] args) throws InterruptedException {
		Map<Key, Object> map = new HashMap<>();
		//Map<Key, Object> map = new ConcurrentHashMap();
		for(int i=0; i< COUNT; i++) {
			es.submit(new Runnable() {
				@Override
				public void run() {
					try {
						map.put(new Key(), new Object());
					} catch (Exception e) {
					} finally {
						cdl.countDown();
					}
				}
			});
		}
		cdl.await();
		es.shutdown();
		
		System.err.println("--->" + (COUNT == map.size())); //sometimes true (expected), sometimes false
	}

	static class Key{
		@Override
		public boolean equals(Object arg0) {
			return false;
		}

		@Override
		public int hashCode() {
			return 0; //same hashcode
		}
	}
}

