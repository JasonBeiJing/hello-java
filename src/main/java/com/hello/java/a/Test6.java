package com.hello.java.a;

// char byte short int + Character Byte Short Integer + String + enum = 10 种类型
public class Test6 {

	static enum XX{
		A,B
	}
	
	public static void main(String[] args) {
		XX ans = XX.A;
		switch (ans) {
		case A:
			System.out.println("===");
			break;
		default:
			break;
		}
		
		
		
	}
}
