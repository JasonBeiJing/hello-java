package com.hello.java.sorting.util;

import java.util.Random;

public class RandomArrayUtil {
	private static final Random rd = new Random();

	public static int[] getRandomInt(int length) {
		
		int[] out = new int[length];
		for(int i=0; i<length; i++) {
			out[i] = rd.nextInt(length);
		}
		return out;
	}

}
