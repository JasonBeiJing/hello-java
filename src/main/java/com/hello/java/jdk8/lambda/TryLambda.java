package com.hello.java.jdk8.lambda;

/**
 * (argument) -> (body)
 * 
 * (arg1, arg2...) -> { body }
 * (type1 arg1, type2 arg2...) -> { body }
 *
 */
public class TryLambda {
	public static void main(String args[]) {
		//*****************************无参数**********************************//
		HelloService helloService1 = () -> System.out.println("无参数，一条语句");
		helloService1.sayHi();
		//无参数,如果Lambda表达式的主体多于一条语句，那么需要大括号{}
		HelloService helloService2 = () -> {
			System.out.println("有参数，多条语句 - 1");
			System.out.println("有参数，多条语句 - 2");
		};
		helloService2.sayHi();
		
		//*****************************有单个参数，可以无参数括号**********************************//
		GreetingService greetService1 = (message) -> System.out.println(message);
		greetService1.sayMessage("只有一个参数，可以用括号");
		//如果只有一个参数，可以不用括号
		GreetingService greetService2 = message -> System.out.println( message);
		greetService2.sayMessage("只有一个参数，可以不用括号");
	
		//*****************************有多个参数，必须有参数括号**********************************//
		//类型声明
		MathOperation addition = (int a, int b) -> a + b;
		System.out.println("有返回值，多个参数，必须有参数括号，声明类型： 10 + 5 = " + addition.operation(10, 5));
		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;
		System.out.println("有返回值，多个参数，必须有参数括号，不声明类型：10 - 5 = " + subtraction.operation(10, 5));
	}

	@FunctionalInterface
	interface MathOperation {
		int operation(int a, int b);
	}

	@FunctionalInterface
	interface GreetingService {
		void sayMessage(String message);
	}
	
	@FunctionalInterface
	interface HelloService{
		void sayHi();
	}
}