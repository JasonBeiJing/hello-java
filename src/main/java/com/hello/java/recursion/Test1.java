package com.hello.java.recursion;

/**
 * 1 行为一致
 * 2 边界值停止
 * @author wangshujie
 *
 */
public class Test1 {

	public static void main(String[] args) {
		System.out.println(factorial(5)); // 阶乘
		multipl(9); // 99乘法表
	}
	
	private static long factorial(long n) {
		if (n == 1) {
			return 1L;
		}
		return n * factorial((n-1));
	}
	
	private static void multipl(int n) {
		if (n == 1) {
			System.out.println("1 * 1");
		} else {
			multipl(n - 1);
			for (int i = 1; i <= n; i++) {
				System.out.print("| " + i + " * " + n + " | ");
			}
		}
		System.out.println("");
	}
}
