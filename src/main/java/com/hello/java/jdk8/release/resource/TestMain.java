package com.hello.java.jdk8.release.resource;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;

public class TestMain {

	public static void main(String[] args) {
		// jdk7
		InputStream is = null;
		OutputStream os = null;
		try {
			is = new FileInputStream("/home/biezhi/a.txt");
			os = new FileOutputStream("/home/biezhi/b.txt");
			char charStr = (char) is.read();
			os.write(charStr);
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				is.close();
				os.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}

		// jdk8
		// 只要是实现了AutoCloseable接口就可以在try语句块退出的时候自动调用close()方法关闭流资源
		try (InputStream iss = new FileInputStream("/home/biezhi/a.txt");
				OutputStream oss = new FileOutputStream("/home/biezhi/b.txt")) {
			char charStr = (char) iss.read();
			oss.write(charStr);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
