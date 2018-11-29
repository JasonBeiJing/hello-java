package com.hello.java.spi;

import java.util.ServiceLoader;

public class TestMain {

	public static void main(String[] args) {
		 ServiceLoader<TestSPI> loaders = ServiceLoader.load(TestSPI.class);
	        for (TestSPI d : loaders) {
	            d.test();
	        }
	}

}
