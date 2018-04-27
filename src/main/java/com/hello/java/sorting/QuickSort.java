package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

/**
 * 快速排序流程： (1) 从数列中挑出一个基准值。 (2)
 * 将所有比基准值小的摆放在基准前面，所有比基准值大的摆在基准的后面(相同的数可以到任一边)；在这个分区退出之后，该基准就处于数列的中间位置。 (3)
 * 递归地把"基准值前面的子数列"和"基准值后面的子数列"进行排序。
 * 
 * https://www.cnblogs.com/skywang12345/p/3596746.html
 * 
 * @author wangshujie
 *
 */
public class QuickSort {

	public static void main(String[] args) {
		int a[] = RandomArrayUtil.getRandomInt(1);
		quickSort(a, 0, a.length-1);
		
		for (int i = 0; i < a.length; i++) {
			System.err.println(a[i]);
		}
	}

	public static void quickSort(int[] a, int i, int j) {
		if (i < j) {
			int x;
			x = a[i];
			while (i < j) {
				// 从右向左找第一个小于x的数
				while (i < j && a[j] > x) {					
					j--; 
				}
				if (i < j) {
					a[i++] = a[j];
				}
				
				// 从左向右找第一个大于x的数
				while (i < j && a[i] < x) {					
					i++; 
				}
				if (i < j){
					a[j--] = a[i];
				}
					
			}
			a[i] = x;
			//必须保证操作的是同一个数组a的不同区间
			quickSort(a, i, i - 1); 
			quickSort(a, i + 1, j); 
		}
	}
}
