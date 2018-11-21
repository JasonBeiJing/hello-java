package com.hello.java.twob;

import java.util.concurrent.TimeUnit;

public class AlibabaTest {

	private boolean odd;

	public static void main(String[] args) {
		// 思路：wait - notify 来控制线程互相等待
		// 多个线程共享一把锁
		AlibabaTest ali = new AlibabaTest();
		
		new Thread(new OddTask(ali)).start();
		new Thread(new EvenTask(ali)).start();
	}
	
	// 打印奇数的线程任务
	static class OddTask implements Runnable {
		// shared lock
		private AlibabaTest lock;

		public OddTask(AlibabaTest lock) {
			this.lock = lock;
		}
		
		public void run() {
			int i = 1;
			while (i < 20) {
				synchronized (lock) {
					if (!lock.odd) {
						System.err.println("-->奇数-->" + i);
						i += 2; // 下一个奇数
						lock.odd = true;
						lock.notify();
						
						//休息一下，否则执行太快，控制台数字会乱，单纯为了打印看出效果
						try {
							TimeUnit.MILLISECONDS.sleep(200);
						} catch (Exception e) {
						}
					} else {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}

		}
	}

	// 打印偶数的线程任务
	static class EvenTask implements Runnable {
		// shared lock
		private AlibabaTest lock;

		public EvenTask(AlibabaTest lock) {
			this.lock = lock;
		}

		public void run() {
			int i = 2;
			while (i <= 20) { // 注意最后一个元素为20，需要=
				synchronized (lock) {
					if (lock.odd) {
						System.out.println("-->偶数-->" + i);
						i += 2; // 下一个偶数
						lock.odd = false;
						lock.notify();
						
						//休息一下，否则执行太快，控制台数字会乱，单纯为了打印看出效果
						try {
							TimeUnit.MILLISECONDS.sleep(200);
						} catch (Exception e) {
						}
					} else {
						try {
							lock.wait();
						} catch (InterruptedException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						}
					}
				}
			}
		}
	}
	

}