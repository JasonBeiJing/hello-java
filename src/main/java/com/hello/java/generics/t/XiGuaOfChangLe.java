package com.hello.java.generics.t;

public class XiGuaOfChangLe extends XiGua<Integer> {

	//昌乐西瓜用Integer
	
	public static void main(String[] args) {
		XiGuaOfChangLe a = new XiGuaOfChangLe();
		a.setFruit(10);
		//setFruit(10L); 不接受Long, 编译错误
		//Integer === 10
		System.err.println(a.getFruit().getClass().getSimpleName() + " === " + a.getFruit());
	}
}
