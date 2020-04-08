package com.hello.java.a;

public class Test4 extends Thread{

	// 只能保证one在two前面输出
	public static void main(String[] args) {
		Test4 t1 = new Test4();
		Test4 t2 = new Test4();
		t1.start();
		System.out.println("one");
		t2.start();
		System.out.println("two");
	}

	public void run() {
		System.out.println("Thread");
	}
}
