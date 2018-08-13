package com.hello.java.jdk8.lambda;

/**
 * (argument) -> (body)
 * 
 * (arg1, arg2...) -> { body }
 * (type1 arg1, type2 arg2...) -> { body }
 *
 */
public class TryLambda1 {
	public static void main(String args[]) {
		// 类型声明
		MathOperation addition = (int a, int b) -> a + b;
		// 不用类型声明
		MathOperation subtraction = (a, b) -> a - b;
		// 大括号中的返回语句
		MathOperation multiplication = (int a, int b) -> {
			return a * b;
		};
		// 没有大括号及返回语句
		MathOperation division = (int a, int b) -> a / b;

		System.out.println("10 + 5 = " + operate(10, 5, addition));
		System.out.println("10 - 5 = " + operate(10, 5, subtraction));
		System.out.println("10 x 5 = " + operate(10, 5, multiplication));
		System.out.println("10 / 5 = " + operate(10, 5, division));

		// 不用括号
		GreetingService greetService1 = message -> System.out.println("Hello, " + message);
		greetService1.sayMessage("Runoob");
		// 用括号
		GreetingService greetService2 = (message) -> System.out.println("Hello, " + message);
		greetService2.sayMessage("Google");
		
		//无参数,如果Lambda表达式的主体多于一条语句，那么需要大括号{}
		HelloService helloService = () -> {
			System.out.println("Hello, world!");
			System.out.println("Hello, China!");
		};
		helloService.sayHi();
	}

	@FunctionalInterface
	interface MathOperation {
		int operation(int a, int b);
	}

	interface GreetingService {
		void sayMessage(String message);
	}
	
	@FunctionalInterface
	interface HelloService{
		void sayHi();
	}

	static int operate(int a, int b, MathOperation mathOperation) {
		return mathOperation.operation(a, b);
	}
}