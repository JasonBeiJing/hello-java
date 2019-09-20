package com.hello.java.a;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(new BufferedInputStream(System.in));
		while (input.hasNext()) {
			int a = (int) input.nextInt();
			int b = (int) input.nextInt();
			System.out.println((a + 1) * (b - 1));
		}
	}
}
