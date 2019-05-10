package com.hello.java.generics.t;

public class Apple extends Fruit<Double> {
	
	public static void main(String[] args) {
		Apple a = new Apple();
		a.setFruit(12D);
		//setFruit("12"); 不接受String, 编译错误
		//Double === 12.0
		System.err.println(a.getFruit().getClass().getSimpleName() + " === " + a.getFruit());
	}
}
