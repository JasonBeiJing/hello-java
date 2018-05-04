package com.hello.java.sorting.search;

import com.hello.java.sorting.InsertSort;
import com.hello.java.sorting.util.RandomArrayUtil;

public class BinarySearch {

	public static void main(String[] args) {
		int[] a = RandomArrayUtil.getRandomInt(1000);
		InsertSort.sort(a);
		
		int value = a[666];
		
		int index1 = search1(a, value);
		System.err.println(index1 + "------->" + (index1 > 0 ? a[index1] : "hmmmmm..."));
		
		int index2 = search2(a, value, 0, a.length-1);
		System.out.println(index2 + "------->" + (index2 > 0 ? a[index2] : "hmmmmm..."));
	}
	
	public static int search1(int[] a, int value) {
		int start = 0;
		int end = a.length - 1;
		while (start <= end) {
			int middle = (start + end) / 2;
			if(a[middle] == value) {
				return middle;
			}else if(a[middle] < value) {
				start = middle + 1;
			}else if(a[middle] > value) {
				end = middle - 1;
			}
		}
		return -1;
	}
	
	public static int search2(int[] a, int value, int start, int end) {
		int middle = (start + end) / 2;
		if(a[middle] == value) {
			return middle;
		}else if(a[middle] < value) {
			return search2(a, value, start = middle + 1, end);
		}else if(a[middle] > value) {
			return search2(a, value, start, end = middle - 1);
		}
		return -1;
	}
}
