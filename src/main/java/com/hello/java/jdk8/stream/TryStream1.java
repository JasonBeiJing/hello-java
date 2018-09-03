package com.hello.java.jdk8.stream;

import java.util.Arrays;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.DoubleStream;
import java.util.stream.IntStream;
import java.util.stream.LongStream;
import java.util.stream.Stream;

public class TryStream1 {

	public static void toStream() {
		// 1. Individual values
		Stream<String> s1 = Stream.of("a", "b", "c");
		// 2. Arrays
		String[] strArray = new String[] { "a", "b", "c" };
		Stream<String> s2 = Stream.of(strArray);
		Stream<String> s3 = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		Stream<String> s4 = list.stream();
	
		//IntStream、LongStream、DoubleStream
		IntStream s5 = IntStream.of(1, 2, 3);
		IntStream s6 = IntStream.range(10, 20);
		
		LongStream s7 = LongStream.of(1L, 2L, 3L);
		LongStream s8 = LongStream.range(10L, 20L);
		
		DoubleStream s9 = DoubleStream.of(0.1, 2.0, 3.0);
	}
	
	public static void fromStream() {
		//Array
		Object[] s1 = Stream.empty().toArray();
		
		//Collection
		List<Object> s2 = Stream.empty().collect(Collectors.toList());
		List<Object> s22 = Stream.empty().collect(Collectors.toCollection(() -> new LinkedList<>()));
		System.err.println(s2.getClass().getCanonicalName()+"------LIST------->" + s22.getClass().getCanonicalName());
		Set<Object> s3 = Stream.empty().collect(Collectors.toSet());
		Set<Object> s33 = Stream.empty().collect(Collectors.toCollection(LinkedHashSet::new));
		System.err.println(s3.getClass().getCanonicalName()+"-------SET------>" + s33.getClass().getCanonicalName());
		
	}

	public static void main(String[] args) {
		fromStream();
	}
}
