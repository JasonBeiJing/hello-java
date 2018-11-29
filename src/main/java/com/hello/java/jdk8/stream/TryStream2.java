package com.hello.java.jdk8.stream;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class TryStream2 {

	public static void main(String[] args) {
		List<Integer> list = Arrays.asList(1, 2, 3, 4);
		System.err.println("Stream-Max: " + list.parallelStream().max(Comparator.comparing(Integer::intValue)).get());
		System.err.println("Stream-Min: " + list.parallelStream().min(Comparator.comparing(Integer::intValue)).get());

		//reduce: 返回单个的结果值，并且reduce操作每处理一个元素总是创建一个新值而不修改原值
		System.err.println("Stream-reduce: " + list.stream().reduce(100, (m, n) -> m + n)); //100起始值
		System.err.println("Stream-reduce: " + list.stream().reduce((m, n) -> m + n).get()); //无起始值
		
		//peek无返回值，map有返回值
		Stream.of("one", "two", "three", "four")
	              .filter(e -> e.length() > 3)
	              .peek(e -> System.out.println("Filtered value: " + e))
	              .map(String::toUpperCase)
	              .peek(e -> System.out.println("Mapped value: " + e))
	              .collect(Collectors.toList());
		
		
		System.err.println("Stream-sorted-naturalOrder: " + list.parallelStream().sorted(Comparator.naturalOrder()).collect(Collectors.toList()));
		System.err.println("Stream-sorted-reverseOrder: " + list.parallelStream().sorted(Comparator.reverseOrder()).collect(Collectors.toList()));
		
		//unordered操作不会进行任何显式的打乱流的操作。它的工作是：消除流中必须保持的有序约束，因此允许之后的操作使用不必考虑有序的优化。
		System.err.println("Stream-sorted-unordered: " + list.parallelStream().unordered().collect(Collectors.toList()));

		
		//list.parallelStream().spliterator();
//		list22.parallelStream().forEach(action);
//		list22.parallelStream().forEachOrdered(action);
//		list22.parallelStream().sequential();
//		list22.parallelStream().dropWhile(predicate);		
	}
}
