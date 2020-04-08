package com.hello.java.a;

public class Test5 {
	
	static class Base{
		
		public int id = 100;
		public void doSomething() {
			System.out.println("Base");
		}
		
		public int getId() {
			return id;
		}
	}
	
	static class Child extends Base{
		public int id = 101;

		@Override
		public void doSomething() {
			System.out.println("Child");
		}
		
		@Override
		public int getId() {
			return id;
		}
		
		public static void main(String[] args) {
			Base base = new Child();
			System.out.println(base.id + " ==> " + base.getId());
			base.doSomething();
		}
	}

}
