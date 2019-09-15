package com.hello.java.jdk8.lambda;

import java.io.PrintStream;
import java.util.Comparator;
import java.util.function.BiPredicate;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Supplier;

import org.junit.Test;

/**
 * 方法引用: 【实例::实例方法名】 【类::静态方法名】 【类::实例方法名】
		①方法引用所引用的方法的参数列表与返回值类型，需要与函数式接口中抽象方法的参数列表和返回值类型保持一致！
		②若Lambda的参数列表的第一个参数，是实例方法的调用者，第二个参数(或无参)是实例方法的参数时，格式： 【类::实例方法名】
 *
 * 构造器引用: 【类:new】
 * 
 * 数组引用: 【类[]::new】
 * 
 * 方法引用可以理解为Lambda表达式的另外一种表现形式
 */
public class LambdaDoubleColons {

	// 实例::实例方法名
	@Test
	public void test1() {
		// -------------------------------------------------
		PrintStream ps = System.out;
//		new Consumer<String>() {
//			@Override
//			public void accept(String s) {	
//				ps.print(s);
//			}
//		};
		Consumer<String> x = s -> ps.println(s);
		x.accept("y");
		// 等价
		Consumer<String> y = ps::println;
		y.accept("y");
		// 等价
		Consumer<String> z = System.out::println;
		z.accept("z");
		
		// -------------------------------------------------
		//A.getName()的输入输出参数，需与Supplier.get()的输入输出参数保持一致
		A a = new A("Beijing");
//		new Supplier<String>() {
//			@Override
//			public String get() {
//				return a.getName();
//			}
//			
//		};
		Supplier<String> s1 = () -> a.getName();
		Supplier<String> s2 = a::getName;
		System.out.println(s1.get() + " == 等价 == " + s2.get());
	}

	//类::静态方法名
	@Test
	public void test2() {
		Supplier<String> supplier = A::getSth;
		System.out.println(supplier.get());
		
		//如下两种声明方式，也等价
		Comparator<Integer> com1 = (x, y) -> Integer.compare(x, y);
		Comparator<Integer> com2 = Integer::compare;
	}
	
	//类::实例方法名
	@Test
	public void test3() {
		//如下两种声明方式等价，但是规则是：x是String中equals(实例方法，非static声明)的调用者,y是equals的参数时
		BiPredicate<String, String> bp = (x, y) -> x.equals(y);
		BiPredicate<String, String> bp2 = String::equals;
		
		Function<Integer, Long> f1 = x -> x.longValue();
		Function<Integer, Long> f2 = Integer::longValue;
	}

	//构造器引用
	@Test
	public void test4() {
		//需要调用的构造器参数列表需与函数式接口的参数列表一致，即调用哪个构造器，取决于函数式接口的输入输出参数。

		//如下两种声明方式等价: 调用无参构造器,
		Supplier<A> s1 = () -> new A();
		Supplier<A> s2 = A::new;
	
		//如下两种声明方式等价: 调用有参构造器
		Function<String, A> f1 = name -> new A(name);
		Function<String, A> f2 = A::new;
		
	}
	
	//数组引用
	@Test
	public void test5() {
		Function<Integer, String[]> fun = size -> new String[size];
		
		Function<Integer, String[]> fun2 = String[]::new;
	}

	static class A {
		private String name;

		public A() {
			super();
		}

		public A(String name) {
			super();
			this.name = name;
		}

		public static String getSth() {
			return "hello, lambda!";
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}
	}
}
