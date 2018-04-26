package com.hello.java.sorter;

public class MaoPao {

	public static void main(String[] args) {
		int a[] = { 49, 38, 65, 97, 76, 13, 27, 49, 78, 34, 12, 64, 5, 4, 62, 99, 98, 54, 56, 17, 18, 23, 34, 15, 35, 25, 53, 51 };

		//sort(a);
		sort2(a);
		
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void sort(int[] a) {
		int temp = 0;
		int length = a.length;
		for (int i = length - 1; i > 0; --i) {
			for (int j = 0; j < i; ++j) {
				if (a[j + 1] < a[j]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
	
	public static void sort2(int[] a) {
		int temp = 0;
		int length = a.length;
		for (int i = 0; i < length - 1; i++) {
			for (int j = 0; j < length - 1 - i; j++) {
				if (a[j] > a[j + 1]) {
					temp = a[j];
					a[j] = a[j + 1];
					a[j + 1] = temp;
				}
			}
		}
	}
}
