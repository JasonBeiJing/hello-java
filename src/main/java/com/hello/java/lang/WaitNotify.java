package com.hello.java.lang;

public class WaitNotify {

	public boolean flag;

	static class OddTask implements Runnable {
		public WaitNotify t;
		public OddTask(WaitNotify t) {
			this.t = t;
		}

		@Override
		public void run() {
			int i = 1;
			while (i < 100) {
				synchronized (t) {
					if (!t.flag) {
						System.out.println("-----" + i);
						i += 2;
						t.flag = true;
						t.notify();
					} else {
						try {
							t.wait();
						} catch (InterruptedException e) {
						}
					}
				}
			}
		}
	}

	static class EvenTask implements Runnable {
		public WaitNotify t;
		public EvenTask(WaitNotify t) {
			this.t = t;
		}

		@Override
		public void run() {
			int i = 2;
			while (i <= 100)
				synchronized (t) {
					if (t.flag) {
						System.out.println("-----------" + i);
						i += 2;
						t.flag = false;
						t.notify();
					} else {
						try {
							t.wait();
						} catch (InterruptedException e) {
						}
					}
				}
		}
	}

	public static void main(String[] args) {
		WaitNotify wn = new WaitNotify();
		OddTask odd = new OddTask(wn);
		EvenTask even = new EvenTask(wn);
		new Thread(odd).start();
		new Thread(even).start();
	}

}
