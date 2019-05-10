package com.hello.java.generics.z;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class TongPeiFu2 {

	public static void main(String[] args) throws InterruptedException {
		List<Cat> list0 = new ArrayList<>();
		list0.add(new Cat());
		
		//一
		//?可以匹配任何字符, List<?>一般作为参数来接收外部的集合，或者返回一个不知道具体元素类型的集合
		List<?> list1 = new ArrayList<>();
		//list1.add(new Animal()); //不能添加任何元素 
		list1.add(null); //null值除外
		list1 = list0; //但是，可以接受任何类型的的集合引用赋值
		list1.forEach(n->{
			System.out.println("list1 : ----> " + n.getClass().getCanonicalName());
		});
		TimeUnit.MILLISECONDS.sleep(10);
		
		//二
		//List<T> :  接受T以及T的子类
		List<Animal> list2 = new ArrayList<>();
		list2.add(new Animal());
		list2.add(new Cat());
		list2.add(new Garfield());
		list2.forEach(n->{
			System.err.println("list2 : ----> " + n.getClass().getCanonicalName());
		});
		TimeUnit.MILLISECONDS.sleep(10);
		
		//三
		//List<? extends T> : ?为T或者T的子类，上界为T, 除了null值，不能添加任何元素。取出的数据向上转化为T,范型没丢失。Get first
		//也就是说：List<? extends T> 唯一能保证的是我们从这个list中读取的元素一定是一个T类型的，无法确定被添加的元素类型是什么。如：List<? extends Number>，无法添加double或者long，因为我们不能确定该集合最终存的是List<Double>还是List<Long>
		List<? extends Animal> list3 = new ArrayList<>();
		//list2.add(new Animal()); 
		list3 = list0;
		Animal a1 = list3.get(0);
		System.out.println("list3 : ----> " + a1.getClass().getSimpleName());
		TimeUnit.MILLISECONDS.sleep(10);
		
		//四
		//List<? super T> : ?为T或者T的父类，下界为T。取出的数据转化为Object,范型丢失。Put first
		//易混淆点: 有一点需要注意的是, List<? super T> 和 List<? extends T> 中, 我们所说的XX是T的父类(a superclass of T) 或XX是T的子类(a subclass of T) 其实是针对于【泛型参数】而言的. 
		List<? super Cat> list4 = new ArrayList<>();
		list4.add(new Cat()); //List<? super Cat> -> List<Cat>,那么自然可以添加Cat对象以及Garfield对象元素
		list4.add(new Garfield());
		//list4.add(new Animal()); //编译报错: List<? super Cat> -> List<父类> 我们不知道Cat会存在怎么的父类（Cat可能存在多个父类），所以无法添加Animal 
		list4.forEach(n->{
			System.err.println("list4 : ----> " + n.getClass().getCanonicalName());
		});
	}
	
	static class Animal{}
	static class Cat extends Animal{}
	static class Garfield extends Cat{}
}
