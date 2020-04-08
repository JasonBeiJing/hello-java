package com.hello.java.b;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Test1 {
	public static void main(String[] args) {
		Scanner cin = new Scanner(System.in, "UTF-8");
		int incompatible = cin.nextInt();
		int containerNum = cin.nextInt();
		
		int[][] incompatibility = new int[incompatible][];
		for (int i = 0; i < incompatible; i++) {
			int cargoId1 = cin.nextInt();
			int cargoId2 = cin.nextInt();
			incompatibility[i] = new int[] {cargoId1, cargoId2};
		}
		
		List<Integer>[] containers = new List[containerNum];
		for (int i = 0; i < containerNum; i++) {
			int cargoNum = cin.nextInt();
			containers[i] = new ArrayList<>(cargoNum);
			for (int j = 0; j < cargoNum; j++) {
				containers[i].add(cin.nextInt());
			}
		}
		
		cin.close();
		
		boolean[] results = isSuccess(incompatibility, containers);
		if (results != null) {
			for (boolean result : results) {
				System.out.println(result ? "Yes" : "No");
			}
		}
	}

	static boolean[] isSuccess(int[][] incompatibility, List<Integer>[] containers) {
		int containerSize = containers.length;
		int incompatibilitySize = incompatibility.length;
		boolean[] result = new boolean[containerSize];
		for (int i = 0; i < containerSize; i++) {
			List<Integer> ms = containers[i]; // 一个集装箱的货物
			boolean re = false;
			for (int j = 0; j < incompatibilitySize; j++) {
				int[] subIncompatibility = incompatibility[j]; // 一个不相容清单
				int subLength = subIncompatibility.length;
				List<Integer> toList = new ArrayList<>(subLength);
				for (int k = 0; k < subLength; k++) {
					int sub = subIncompatibility[k];
					toList.add(sub);
				}
				re = ms.containsAll(toList);
				if (re) {
					break;
				}
			}
			result[i] = !re;
		}
		return result;
	}
}
