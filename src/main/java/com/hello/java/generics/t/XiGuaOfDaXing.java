package com.hello.java.generics.t;

public class XiGuaOfDaXing extends XiGua<Long> {

	//大兴西瓜用Long

	public static void main(String[] args) {
		XiGuaOfDaXing a = new XiGuaOfDaXing();
		a.setFruit(10L);
		//setFruit(10); 不接受Integer, 编译错误
		//Long === 10
		System.err.println(a.getFruit().getClass().getSimpleName() + " === " + a.getFruit());
	}
}
