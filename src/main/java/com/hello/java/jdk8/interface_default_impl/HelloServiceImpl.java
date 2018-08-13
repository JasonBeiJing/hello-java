package com.hello.java.jdk8.interface_default_impl;

public class HelloServiceImpl implements HelloService {

	@Override
	public void hi() {
		System.err.println(" ==========================  ");
	}

	@Override
	public void hey() {
		System.err.println(" **************************  ");
	}
	
	public static void main(String[] args) {
		HelloService hs = new HelloServiceImpl();
		hs.hi();
		hs.hey();
		hs.hello("Beijing");
	}

}
