package com.hello.java.a;

public class Test2 {

	public static void main(String[] args) {
		int n = 2;
		System.out.println(func(n));

	}

	private static int func(int n) {
		int[] number = { 1, 2, 3, 4 };
		try {
			int ret = number[n] * 2;
			return ret;
		} catch (Exception e) {
			return n * 3;
		} finally {
			if (n == 2) {
				return 0;
			}
		}
	}
}
