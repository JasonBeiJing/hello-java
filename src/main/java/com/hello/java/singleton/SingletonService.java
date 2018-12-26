package com.hello.java.singleton;

/**
 *	1. 采用类锁，保证全局锁的唯一性
 *	2. 采用volatile关键字，禁止重排序
 *		2.1 为instance分配内存空间
 *		2.2 初始化instance
 *		2.3 将instance指向分配的内存地址
 *		在多线程环境下，操作系统会对指令进行重排序，有可能会出现2.1 -> 2.3 -> 2.2的情况，所以采用volatile禁止其重排序，保证取到单实例对象
 */


public class SingletonService {
	private volatile static SingletonService instance = null;

	private SingletonService() {}
	
	public SingletonService getInstance() {
		if(instance == null) {
			synchronized(SingletonService.class) {
				if(instance == null) {
					instance = new SingletonService();
				}
			}
		}
		return instance;
	}
}
