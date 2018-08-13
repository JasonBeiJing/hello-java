package com.hello.java.jdk8.interface_default_impl;

public interface HelloService {

	void hi();
	void hey();
	
	//default is mandatory
	default void hello(String name) {
		System.out.println("hello, " +name);
	}
}
