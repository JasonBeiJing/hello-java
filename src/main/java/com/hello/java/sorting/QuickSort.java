package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

public class QuickSort {

	public static void main(String[] args) {
		int a[] = RandomArrayUtil.getRandomInt(100);
		quickSort(a, 0, a.length - 1);

		for (int i = 0; i < a.length; i++) {
			System.err.println(a[i]);
		}
	}

	public static void quickSort(int[] a, int l, int r) {
		if (l < r) {
			int i, j, x;
			i = l;
			j = r;
			x = a[i];
			while (i < j) {
				while (i < j && a[j] > x) {					
					j--; // 从右向左找第一个小于x的数
				}
				if (i < j) {					
					a[i++] = a[j];
				}
				while (i < j && a[i] < x) {					
					i++; // 从左向右找第一个大于x的数
				}
				if (i < j) {					
					a[j--] = a[i];
				}
			}
			a[i] = x;
			quickSort(a, l, i - 1); 
			quickSort(a, i + 1, r); 
		}
	}
}
