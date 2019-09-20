package com.hello.java.a;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			String string = (String) scanner.next();
			Map<String, String> map = new HashMap<>();
			map.put("老虎", "棒子");
			map.put("棒子", "虫");
			map.put("虫", "鸡");
			map.put("鸡", "老虎");
			String[] array = string.split(",");
			StringBuilder stringBuilder = new StringBuilder();
			for (int i = 0; i < array.length; i++) {
				stringBuilder.append(map.get(array[i]) + ",");
			}
			if (stringBuilder.length() > 0) {
				System.out.println(stringBuilder.substring(0, stringBuilder.length() - 1));
			}
		}
	}

}
