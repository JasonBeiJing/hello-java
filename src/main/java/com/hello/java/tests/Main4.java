package com.hello.java.tests;

import java.io.BufferedInputStream;
import java.util.Scanner;

//螺旋三角
public class Main4 {

	private static int[][] a; // 声明一个静态成员变量，用于保存三角形的对应位置的数值。

	public static void main(String[] args) {
		System.out.println("请输入一个值N：");
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int num = scanner.nextInt();
		a = new int[num][num];
		sj(0, 0, 1, num); // 调用方法
		for (int i = 0; i < a.length; i++) { // 将数组输出，并处理输出形式
			for (int j = 0; j < a[i].length; j++) {
				if (a[i][j] != 0) {
					if (a[i][j] < 10) {
						System.out.print(" " + a[i][j] + "  ");
					} else if (a[i][j] < 100) {
						System.out.print(a[i][j] + "  ");
					} else
						System.out.print(a[i][j] + " ");
				}
			}
			System.out.println();
		}
	}

	public static void sj(int i1, int j1, int k1, int n1) {
		int k = k1; // 数值
		int n = n1;
		for (int i = i1; i < n; i++) { // 外层行循环
			for (int j = j1; j < n - i; j++) { // 内层列循环
				if (i == i1) { // 当i=i1的时候，将一行的未赋值列填充数值,如"1 2 3 4 5"
					a[i][j] = k++;
				} else if (i == n - i1 - 1) { // 从下至上的填充数值行，如9->10->11->12,向上填值
					int tempJ = j1; // 临时的J
					int tempI = i; // 临时的I
					while (a[tempI][tempJ] == 0) { // 判断这个数组元素是否为0，为0则代表没被使用过
						a[tempI][tempJ] = k++; // 将K赋值给这一相应的数组位置。
						tempI--; // 将行数减去1，实现向上填值的作用。
					}
					sj(i1 + 1, j1 + 1, k, n - 1); // 当以上步骤完成之后，就说明完成了三角形的赋值路线。然后调用自身方法，再走一次三角形路线
				} else { // 输出从上至左下斜行
					j = n - i - 1; // j的值为三角形最外边的一列
					a[i][j] = k++;
					break;
				}
			}
		}
	}
}
