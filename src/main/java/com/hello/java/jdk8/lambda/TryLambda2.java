package com.hello.java.jdk8.lambda;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class TryLambda2 {

	public static void main(String[] args) {
		List<String> list = new ArrayList<>(3);
		list.add("111");
		list.add("222");
		list.add("333");
		list.forEach(n -> System.out.println("---> " + n));
		
		Collections.sort(list, (String s1, String s2) -> s1.compareTo(s2) * -1);
		list.forEach(n -> System.err.println("===> " + n));
		
		new Thread(() -> System.err.println("ahaha")).start();
		
		list.removeIf(s -> s.equalsIgnoreCase("222"));
		
		//new way in java 8
		list.forEach(System.out::println);
	}
}

