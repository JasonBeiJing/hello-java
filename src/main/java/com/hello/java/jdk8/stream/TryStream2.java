package com.hello.java.jdk8.stream;

import java.util.Arrays;
import java.util.List;

public class TryStream2 {

	public static void main(String[] args) {
		List<String> list = Arrays.asList("a", "b", "c", "x", "y", "z");
		
		list.parallelStream().dropWhile(predicate);
		list.parallelStream().dropWhile(predicate);
		
		list.parallelStream().peek(action);
		list.parallelStream().reduce(accumulator);
		list.parallelStream().sorted(comparator);
		list.parallelStream().unordered();
		list.parallelStream().skip(n);
		list.parallelStream().spliterator();
		list.parallelStream().forEach(action);
		list.parallelStream().forEachOrdered(action);
		list.parallelStream().limit(maxSize);
		list.parallelStream().max(comparator);
		list.parallelStream().min(comparator);
		list.parallelStream().sequential();
		
		
	}
}
