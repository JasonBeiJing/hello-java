package com.hello.java.b;

import java.io.BufferedInputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

public class Main9 {
	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		int start = 0;
		int count = 0;
		List<Integer> result = null;
		while (scanner.hasNextLine()) {
			if (start == 0) {
				count = (int) scanner.nextInt();
				result = new ArrayList<>(count);
				start++;
			} else {

				String string = (String) scanner.next();
				Map<Character, Integer> map = getSameCharCount(string);
				result.add(getMaxCount(map));
				if (result.size() == count) {
					for (int i = 0; i < count; i++) {
						System.out.println(result.get(i));
					}
				}

			}

		}
	}

	private static Map<Character, Integer> getSameCharCount(String string) {
		char[] arr = string.toCharArray();
		Map<Character, Integer> map = new HashMap<Character, Integer>();

		char lastChar = ' ';
		Integer lastCount = 0;
		for (char c : arr) {

			if (map.get(c) == null) {
				map.put(c, 1);

			}
			if (c == lastChar) {
				Integer value = map.get(c);
				value++;
				map.put(c, value);
				lastCount = value;
			} else {
				if (lastChar != ' ') {
					int lastCharCount = map.get(lastChar);
					if (lastCharCount < lastCount) {
						map.put(lastChar, lastCount);
					}
				}

				map.put(c, 1);
			}
			lastChar = c;

		}
		return map;
	}

	private static Integer getMaxCount(Map<Character, Integer> map) {
		Set<Character> set = map.keySet();
		Iterator<Character> it = set.iterator();
		int max = 0;
		while (it.hasNext()) {
			Character key = it.next();
			if (map.get(key) > max) {
				max = map.get(key);
			}
		}
		return max;
	}

}