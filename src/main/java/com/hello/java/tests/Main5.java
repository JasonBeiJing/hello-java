package com.hello.java.tests;

import java.io.BufferedInputStream;
import java.util.Scanner;

//给定一个整数n，输出1-n的全排列
public class Main5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (scanner.hasNext()) {
        	dfs(scanner.nextInt());
        }
	}

	public static void dfs(int n) {
		int[] arr = new int[n];
		int[] book = new int[n];
		dfs(0, arr, book);
	}

	private static void dfs(int step, int[] arr, int[] book) {
		if (step == arr.length) {
			for (int i = 0; i < arr.length; i++) {
				System.out.print(arr[i]);
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < arr.length; i++) {
			if (book[i] == 0) {
				arr[step] = i + 1;
				book[i] = 1;
				dfs(step + 1, arr, book);
				book[i] = 0;
			}
		}
	}
}
