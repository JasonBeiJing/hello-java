package com.hello.java.b;

import java.io.BufferedInputStream;
import java.util.Scanner;

public class Main1 {

	public static void main(String[] args) {
		Scanner input = new Scanner(new BufferedInputStream(System.in));
		while (input.hasNext()) {
			System.out.println((input.nextInt() + 1) * (input.nextInt() - 1));
		}
	}
}
