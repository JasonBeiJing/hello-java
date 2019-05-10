package com.hello.java.generics.t;

public class Fruit<E extends Number> {
	private E fruit;

	public E getFruit() {
		return fruit;
	}
	public void setFruit(E fruit) {
		this.fruit = fruit;
	}

	public static void main(String[] args) {
		Fruit<Integer> fruit = new Fruit<>();
		fruit.setFruit(11);
		System.err.println(fruit.getFruit().getClass().getSimpleName() + " ---> " + fruit.getFruit());
		
		test1("1");
		test1(2L);
		
		//test2("1"); 只能为Number以及其子类
		test2(2L);
	}
	
	public static <T> void test1(T t) {
		//String --- test1 --- > 1
		System.err.println(t.getClass().getSimpleName() + " --- test1 --- > " + t.toString());
	}
	
	public static <T extends Number> void test2(T number) {
		//Long --- test2 --- > 2
		System.err.println(number.getClass().getSimpleName() + " --- test2 --- > " + number.toString());
	}
	

}
