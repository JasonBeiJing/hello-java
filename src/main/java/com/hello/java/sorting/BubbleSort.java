package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

public class BubbleSort {

	public static void main(String[] args) {
		int a[] = RandomArrayUtil.getRandomInt(100);
		
		sort(a);
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}
	
	public static void sort(int a[]) {
		int temp = 0;
		for (int i = a.length - 1; i > 0; i--) {
			for (int j = 0; j < i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
}
