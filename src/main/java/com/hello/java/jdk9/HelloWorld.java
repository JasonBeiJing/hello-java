package com.hello.java.jdk9;

import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class HelloWorld {

	public static void main(String[] args) {
		List<String> list = List.of("x", "y");
		System.out.println(list + "---> " + list.getClass().getCanonicalName());
		
		Map<String, Integer> map = Map.of("a", 1, "b", 2);
		System.out.println(map + "===> " + map.getClass().getCanonicalName());
		
		Set<String> set = Set.of("a", "b", "a");
		System.out.println(set + "~~~> " + set.getClass().getCanonicalName());
		
	}
}
