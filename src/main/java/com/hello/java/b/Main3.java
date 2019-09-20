package com.hello.java.b;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main3 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			String input = scanner.nextLine();
			String[] strs = input.split(",");
			if (strs.length == 2) {
				int total = Integer.valueOf(strs[0]) * 20 + Integer.valueOf(strs[1]) * 40;
				int count = total / 4;
				int d1 = count / 5;
				int d2 = (total - d1 * 4) / 8;
				System.out.println(d1 + "," + d2);
			}
		}

	}
}