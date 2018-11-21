package com.hello.java.twob;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class AlibabaTest2 {
	private static final ReentrantLock lock = new ReentrantLock();
	private static final Condition condition = lock.newCondition();

	private static AtomicBoolean odd = new AtomicBoolean(false);
	
	public static void main(String[] args) throws InterruptedException {
		new Thread(new Even()).start();
		new Thread(new Odd()).start();
	}
	
	static class Odd implements Runnable{
		@Override
		public void run() {
			int j = -1;
			while (j < 19) {
				try {
					lock.lock();
					if(!odd.get()) {	
						System.err.println("-----> " + (j += 2));
						TimeUnit.MICROSECONDS.sleep(200);
						odd.set(true);
						condition.signal();
					}else {
						condition.await();
					}
				} catch (Exception e) {
					e.printStackTrace();
				} finally {
					lock.unlock();
				}
			}
		}
	}
	
	static class Even implements Runnable{
		@Override
		public void run() {
			int i = 0;
			while (i < 20) {					
				try {
					lock.lock();
					if(odd.get()) {
						System.out.println("-----> " + (i += 2));
						TimeUnit.MICROSECONDS.sleep(200);
						odd.set(false);
						condition.signal();
					}else {
						condition.await();
					}
				} catch (Exception e) {
					e.printStackTrace();
				}finally {
					lock.unlock();
				}
			}
		}
	}
	
}
