package com.hello.java.b;

import java.io.BufferedInputStream;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Main6 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
        while (scanner.hasNext()) {  
        	String str = scanner.nextLine();
        	if (str == null || str.length() < 1) {
				System.out.println("输入的字符串不合法");
			} else {
				char[] cs = str.toCharArray();
				Set<String> colllector = new HashSet<>();
				calculate(cs, 0, colllector);
				System.out.println(colllector.size());
			}
        }
		
	}

	public static void calculate(char chs[], int start, Set<String> colllector) {
		if (start == chs.length - 1) {
			colllector.add(new String(chs));
		}
		for (int i = start; i <= chs.length - 1; i++) {
			exchange(chs, i, start);
			calculate(chs, start + 1, colllector);
			exchange(chs, i, start);
		}
	}

	public static void exchange(char chs[], int i, int j) {
		char temp;
		temp = chs[i];
		chs[i] = chs[j];
		chs[j] = temp;
	}

}
