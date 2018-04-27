package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

//基本原理如下：
//对于给定的一组记录，经过第一轮比较后得到最小的记录，然后将该记录的位置与第一个记录的位置交换；
//接着对不包括第一个记录以外的其他记录进行第二次比较，得到最小记录并与第二个位置记录交换；重复该过程，知道进行比较的记录只剩下一个为止。


//冒泡排序复杂度相同，但简单选择排序的性能要优于冒泡排序

//选择排序: 简单选择排序、树型选择排序、堆排序
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