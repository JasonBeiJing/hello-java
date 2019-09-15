package com.hello.java.jdk8.interface_default_impl;

public class HelloServiceImpl implements HelloService {

	@Override
	public void hi() {
		System.err.println(" ==========================  ");
	}
	
	public static void main(String[] args) {
		HelloService hs = new HelloServiceImpl();
		hs.hi();
		hs.hello("Beijing");
		HelloService.hey("hello");
	}

}
