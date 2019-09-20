package com.hello.java.a;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main7 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			calculate(scanner.nextInt());
		}
	}

	public static void calculate(int n) {
		int[] level1 = new int[n];
		int[] level2 = new int[n];
		calculate(0, level1, level2);
	}

	private static void calculate(int step, int[] level1, int[] level2) {
		int length = level1.length;
		if (step == length) {
			for (int i = 0; i < length; i++) {
				System.out.print(level1[i]);
			}
			System.out.println();
			return;
		}
		for (int i = 0; i < length; i++) {
			if (level2[i] == 0) {
				level1[step] = i + 1;
				level2[i] = 1;
				calculate(step + 1, level1, level2);
				level2[i] = 0;
			}
		}
	}
}
