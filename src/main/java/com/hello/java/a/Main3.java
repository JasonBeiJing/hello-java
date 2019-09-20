package com.hello.java.a;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			String input = (String) scanner.next();
			String[] array = input.split(",");
			if (array.length == 2) {
				int a = Integer.parseInt(array[0]);
				int b = Integer.parseInt(array[1]);
				;
				int total = a * 20 + b * 40;
				int count = total / 4;
				int d1 = count / 5;
				int d2 = (total - d1 * 4) / 8;
				System.out.println(d1 + "," + d2);
			}
		}

	}
}