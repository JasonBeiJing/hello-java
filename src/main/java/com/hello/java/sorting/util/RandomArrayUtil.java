package com.hello.java.sorting.util;

import java.util.Random;

public class RandomArrayUtil {

	public static int[] getRandomInt(int length) {
		Random rd = new Random();
		
		int[] out = new int[length];
		for(int i=0; i<length; i++) {
			out[i] = rd.nextInt(length);
		}
		return out;
	}

}
