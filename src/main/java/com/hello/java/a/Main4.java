package com.hello.java.a;

import java.io.BufferedInputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class Main4 {

	public static void main(String[] args) {
		Scanner scanner = new Scanner(new BufferedInputStream(System.in));
		while (scanner.hasNext()) {
			String str = scanner.nextLine();
			List<Integer> is = getNumbers(str);
			if (is.size() == 3) {
				int W = is.get(0);
				int S = is.get(1);
				int N = is.get(2);
				
				double value = W * log2(1+S/N);
				
				BigDecimal result = BigDecimal.valueOf(value);
				result = result.setScale(2, BigDecimal.ROUND_HALF_UP);
				System.out.println(result);
			} else {
				System.out.println("输入错误: " + str);
			}
		}
	}
	
	private static double log2(double x) {
        return Math.log(x) / Math.log(2);
	}
	
	private static List<Integer> getNumbers(String str) {
		try {
			if (str != null && str.trim().length() > 1) {
				String str2[] = str.trim().split(",");
				List<Integer> result = new ArrayList<>(3);
				for (String s : str2) {
					result.add(Integer.valueOf(s.trim()));
				}
				return result;
			}
		} catch (Exception e) {
			System.out.println("输入不合法: " + str);
		}
		return Collections.emptyList();
	}

}
