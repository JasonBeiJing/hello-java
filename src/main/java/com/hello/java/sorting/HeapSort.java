package com.hello.java.sorting;

import com.hello.java.sorting.util.RandomArrayUtil;

//形式上是一棵完全二叉树，实际存储在内存中的是一个数组

/**
 * 堆定义: 当一棵完全二叉树的每一个节点都大于(小于)等于它的两个子节点,那么它就是最大(小)堆。
 * 
 */

/**
 * 堆排序的基本思想是：
 * 		将待排序序列构造成一个大顶堆，此时，整个序列的最大值就是堆顶的根节点。
 * 		将其与末尾元素进行交换，此时末尾就为最大值。然后将剩余n-1个元素重新构造成一个堆，这样会得到n个元素的次小值。
 * 		如此反复执行，便能得到一个有序序列了。
 * 		堆排序是一种选择排序
 */

/**
 * 如果对具有n个节点二叉树的根节点从0开始编号，则序号为i的节点的双亲结点为(i-1)/2,左孩子的编号为2i+1，右孩子为2i+2
 * 如果从1开始编号，则双亲结点编号为i/2，左孩子结点序号为2i，右孩子结点序号为2i+1.
 * 
 * https://www.cnblogs.com/chengxiao/p/6129630.html
 *
 */

/**
 * a.将无序序列构建成一个堆，根据升序降序需求选择大顶堆或小顶堆; 
 * b.将堆顶元素与末尾元素交换，将最大元素"沉"到数组末端;
 * c.重新调整结构，使其满足堆定义，然后继续交换堆顶元素与当前末尾元素，反复执行调整+交换步骤，直到整个序列有序。
 *
 */

public class HeapSort {

	public static void main(String[] args) {
		int[] a = RandomArrayUtil.getRandomInt(100);
		sort(a);
		for (int i = 0; i < a.length; i++) {
			System.out.println(a[i]);
		}
		
	}

	public static void sort(int[] arr) {
		// 1.构建大顶堆
		for (int i = arr.length / 2 - 1; i >= 0; i--) {
			// 从第一个非叶子结点从下至上，从右至左调整结构
			adjustHeap(arr, i, arr.length);
		}
		// 2.调整堆结构+交换堆顶元素与末尾元素
		for (int j = arr.length - 1; j > 0; j--) {
			// 将堆顶元素与末尾元素进行交换
			int temp = arr[0];
			arr[0] = arr[j];
			arr[j] = temp;

			adjustHeap(arr, 0, j);// 重新对堆进行调整
		}

	}

	/**
	 * 调整大顶堆（仅是调整过程，建立在大顶堆已构建的基础上）
	 */
	public static void adjustHeap(int[] arr, int i, int length) {
		int temp = arr[i];// 先取出当前元素i
		for (int k = i * 2 + 1; k < length; k = k * 2 + 1) {// 从i结点的左子结点开始，也就是2i+1处开始
			if (k + 1 < length && arr[k] < arr[k + 1]) {// 如果左子结点小于右子结点，k指向右子结点
				k++;
			}
			if (arr[k] > temp) {// 如果子节点大于父节点，将子节点值赋给父节点（不用进行交换）
				arr[i] = arr[k];
				i = k;
			} else {
				break;
			}
		}
		arr[i] = temp;// 将temp值放到最终的位置
	}

}
