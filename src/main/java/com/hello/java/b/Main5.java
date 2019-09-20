package com.hello.java.b;

import java.io.BufferedInputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main5 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			String string = scanner.nextLine();
			Map<String, String> map = new HashMap<>();
			map.put("老虎", "棒子");
			map.put("棒子", "虫");
			map.put("虫", "鸡");
			map.put("鸡", "老虎");
			String[] strs = string.split(",");
			StringBuilder sb = new StringBuilder();
			int length = strs.length;
			for (int i = 0; i < length; i++) {
				sb.append(map.get(strs[i]) + ",");
			}
			if (sb.length() > 0) {
				System.out.println(sb.substring(0, sb.length() - 1));
			}
		}
	}

}
