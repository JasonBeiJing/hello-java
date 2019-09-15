package com.hello.java.jdk8.interface_default_impl;

public interface HelloService {

	void hi();
	
	//default is mandatory
	default void hello(String name) {
		System.out.println("hello, " +name);
	}
	
	static String hey(String str) {
		String re = str + " world!";
		System.err.println(re);
		return re;
	}
	
}
