package com.hello.java.a;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main8 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			String line = scanner.nextLine();
			List<Integer> result= checkInputLine(line);
			if (result.size() != 2) {
				System.out.println("S-2-输入不合法: " + line);
			} else {
				Integer num = result.get(0);
				Integer size = result.get(1);
				if (validateNumber(num) && validateNumber(size)) {
					Integer out = 0;
					for (int i = 1; i <= size; i++) {
						out += getNumber(num, i); 
					}
					System.out.println(out);
				} else {
					System.out.println("S-3-输入不合法: " + line);
				}
			}
		}
	}
	
	private static List<Integer> checkInputLine(String str) {
		try {
			if (str != null && str.trim().length() > 1) {
				String str2[] = str.trim().split(" ");
				List<Integer> result = new ArrayList<>(2);
				for (String s : str2) {
					result.add(Integer.valueOf(s.trim()));
				}
				return result;
			}
		} catch (Exception e) {
			System.out.println("S-1-输入不合法: " + str);
		}
		return Collections.emptyList();
	}

	private static boolean validateNumber(Integer in) {
		if (in >= 1 && in <=9) {
			return true;
		}
		return false;
	}
	
	private static Integer getNumber(Integer num, int length) {
		StringBuilder sb = new StringBuilder(length);
		for (int i = 0; i < length; i++) {
			sb.append(num);
		}
		return Integer.valueOf(sb.toString());
	}
}
