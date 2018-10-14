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

// stream of elements +-----> |filter+-> |sorted+-> |map+-> |collect
public class TryStream {

	private static void stream() {
	
		//生成Stream
		// 1. Individual values
		Stream<String> ss1 = Stream.of("a", "b", "c");
		// 2. Arrays
		String[] strArray = new String[] { "a", "b", "c" };
		Stream<String> ss2 = Stream.of(strArray);
		Stream<String> ss3 = Arrays.stream(strArray);
		// 3. Collections
		List<String> list = Arrays.asList(strArray);
		Stream<String> ss4 = list.stream();
		
		//IntStream、LongStream、DoubleStream
		IntStream ss5 = IntStream.of(1, 2, 3);
		IntStream ss6 = IntStream.range(10, 20);
		LongStream ss7 = LongStream.of(1L, 2L, 3L);
		LongStream ss8 = LongStream.range(10L, 20L);
		DoubleStream ss9 = DoubleStream.of(0.1, 2.0, 3.0);

		
		//Stream生成数组集合
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

	private static void useStream() {
		List<String> list = Arrays.asList("a", "b", "c", "x", "y", "z");
		
		List<String> list2 = list.stream().filter(n -> n.equals("a") || n.equals("x")).map(n -> n + "-heihei").distinct().sorted((t1, t2) -> t1.compareTo(t2) * -1).collect(Collectors.toList()); 
		System.err.println("Result List2 ===> " + list2);
		
		long count = list.stream().filter(n -> n.equals("b") || n.equals("y")).distinct().count();  
		System.err.println("Result List2's Size ===> " + count);
		
		List<String> list3 = list.parallelStream().filter(n -> true).skip(3).collect(Collectors.toList());
		System.out.println("Result List3 ===> " + list3);
		
		
		System.err.println(list.stream().anyMatch(n -> n.equals("c")));
		
		List<User> users = Arrays.asList(new User(1), new User(2), new User(3), new User(1), new User(2));
		Set<Integer> ids = users.parallelStream().map(n -> n.getId()).collect(Collectors.toSet());
		System.out.println("Result IDS ===> " + ids);
	}

	
	private static void mapAndFlatMap() {
		String flatMap = "10001,1;10002,2";
		Arrays.stream(flatMap.split(";")).flatMap(str -> Arrays.stream(str.split(","))).forEach(System.out::println);
		
		String map = "10001,1;10002,2";
		Arrays.stream(map.split(";")).map(str -> str.split(",")).forEach(arr -> System.err.println(Arrays.toString(arr)));
	}

	public static void main(String[] args) {
		stream();
		System.out.println(" ========= ---- ==========  ========= ---- ==========  ========= ---- ==========  ========= ---- ==========");
		useStream();
		
		mapAndFlatMap();
	}
		

	static class User{
		private int id;

		public User(int id) {
			super();
			this.id = id;
		}

		public int getId() {
			return id;
		}
	}
}
