package com.hello.java.concurrent;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Exchanger;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentExchanger {
	private static final ExecutorService executor = Executors.newCachedThreadPool();
	private static final Exchanger<List<String>> exchanger = new Exchanger<>();
	
	public static void main(String[] args) {
		executor.execute(() -> {
			List<String> data = new ArrayList<>();
			data.add("A");
			data.add("B");
			data.add("C");
			data.add("D");
			data.add("E");
			data.add("F");
			data.add("G");
			doExchangeWork(data, exchanger);
		});
		
		executor.execute(() -> {
			List<String> data = new ArrayList<>();
			data.add("X");
			data.add("Y");
			data.add("Z");
			doExchangeWork(data, exchanger);
		});
		
		executor.shutdown();
		
//		10-持有-[A, B, C, D, E, F, G]
//		11-持有-[X, Y, Z]

		//只能保证任意两个线程之间进行交换，所以仅适用于两个线程！！！

	}

	private static void doExchangeWork(List<String> data, Exchanger<List<String>> exchanger) {
		try {
			System.out.println(Thread.currentThread().getId() + "-持有-" + data);
			Thread.sleep((long) (Math.random() * 1000));

			List<String> exchangedData = exchanger.exchange(data);
			System.err.println(Thread.currentThread().getId() + "-收 到 -" + exchangedData);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}
}