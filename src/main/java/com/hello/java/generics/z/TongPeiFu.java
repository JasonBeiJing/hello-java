package com.hello.java.generics.z;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

//参数传递
public class TongPeiFu {

	public static void main(String[] args) {
		List<Long> x = Collections.emptyList();
		List<Number> y = Collections.emptyList();
		List<String> z = Collections.emptyList();
		
		//test0(x); 编译错误, List<Long> x 和 List<Number> in 没有任何关系
		test0(y); 

		//?可以接受任何参数类型
		test1(x);
		test1(y);
		test1(z);
		
		//告诉编译器List<? extends Number> in可以接受集合中的元素类型为Number以及其的子类
		test2(x); 
		test2(y);
		//test2(z); 编译错误，?只能为Number以及其子类
		
		//告诉编译器List<? super Number> in可以接受集合中的元素类型为Number以及其父类
		List<Serializable> parent = new ArrayList<>();
		test3(parent);
		//test3(x); 下限为Number
		test3(y); 
	}
	
	public static void test0(List<Number> in) {}
	
	public static void test1(List<?> in) {}
	
	public static void test2(List<? extends Number> in) {}
	
	public static void test3(List<? super Number> in) {}
}
