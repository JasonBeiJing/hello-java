package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

public class InsertSort {

	public static void main(String[] args) {
		int[] a = RandomArrayUtil.getRandomInt(100000);

		sort(a);

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void sort(int[] a) {
		for (int i = 1; i < a.length; i++) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[i]) {
					int tmp = a[j];
					a[j] = a[i];
					a[i] = tmp;
				}
			}
		}
	}
}
