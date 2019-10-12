package com.hello.java.util;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

public class ReadResource {

	public static void main(String[] args) throws IOException {
		ReadResource rr = new ReadResource();
		Properties properties = new Properties();
		
		readByClass(rr, properties);
		
		readByClassLoader(rr, properties);
	}
	

	/**
	 * path不以'/'开头时，默认是从此类所在的包下取资源；
	 * path以'/'开头时，则是从ClassPath根下获取；
	 */
	private static void readByClass(ReadResource rr, Properties properties) throws IOException {
		URL r1 = rr.getClass().getResource("/messages.properties");
		properties.load((BufferedInputStream)r1.getContent());
		System.out.println("--> " + properties);
		
		InputStream r2 = rr.getClass().getResourceAsStream("/messages.properties");
		properties.load(r2);
		System.out.println("==> " +properties);
	}
	
	/**
	 * path不能以’/'开头时；
	 * path是从ClassPath根下获取；
	 */
	private static void readByClassLoader(ReadResource rr, Properties properties) throws IOException {
		URL r1 = rr.getClass().getClassLoader().getResource("messages.properties");
		properties.load((BufferedInputStream)r1.getContent());
		System.err.println("--> " + properties);
		
		InputStream r2 = rr.getClass().getClassLoader().getResourceAsStream("messages.properties");
		properties.load(r2);
		System.err.println("==> " +properties);
	}
}
