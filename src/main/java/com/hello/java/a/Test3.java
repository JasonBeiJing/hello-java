package com.hello.java.a;

import java.util.ArrayList;
import java.util.List;

public class Test3 {

	public static void main(String[] args) {
		List<String> x = new ArrayList<>();
		List<Object> y = new ArrayList<>();
		System.out.println("A");
		y = x;
		System.out.println("B");
	}

}
