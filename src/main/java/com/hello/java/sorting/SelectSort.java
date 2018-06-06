package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

public class SelectSort {
	public static void main(String[] args) {
		int[] a = RandomArrayUtil.getRandomInt(100);

		simpleSelectSort(a);

		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
	}

	public static void simpleSelectSort(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {// 做第i趟排序
			int minValueIndex = i;
			for (int j = minValueIndex + 1; j < arr.length; j++) {
				if (arr[j] < arr[minValueIndex]) {
					minValueIndex = j; // 记下目前找到的最小值所在的位置
				}
			}
			if (i != minValueIndex) { // 交换a[i]和a[k]
				int temp = arr[i];
				arr[i] = arr[minValueIndex];
				arr[minValueIndex] = temp;
			}
		}
	}

}