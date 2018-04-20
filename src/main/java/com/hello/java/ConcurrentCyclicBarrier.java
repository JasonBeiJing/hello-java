package com.hello.java;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ConcurrentCyclicBarrier {
	private static final ExecutorService es = Executors.newCachedThreadPool();

	public static void main(String[] args) {
		CyclicBarrier cyclicBarrier = new CyclicBarrier(8);

		List<Runner> runners = new ArrayList<>();
		runners.add(new Runner(cyclicBarrier, "博尔特"));
		runners.add(new Runner(cyclicBarrier, "鲍威尔"));
		runners.add(new Runner(cyclicBarrier, "盖伊啊"));
		runners.add(new Runner(cyclicBarrier, "布雷克"));
		runners.add(new Runner(cyclicBarrier, "加特林"));
		runners.add(new Runner(cyclicBarrier, "苏炳添"));
		runners.add(new Runner(cyclicBarrier, "路人甲"));
		runners.add(new Runner(cyclicBarrier, "路人乙"));
		for (Runner runner : runners) {
			es.submit(runner);
		}

		es.shutdown();
	}

	static class Runner implements Runnable {

		private CyclicBarrier cyclicBarrier;
		private String name;

		public Runner(CyclicBarrier cyclicBarrier, String name) {
			super();
			this.cyclicBarrier = cyclicBarrier;
			this.name = name;
		}

		@Override
		public void run() {
			try {
				System.out.println(name + "就位, 等着~~~");
				cyclicBarrier.await(); // 直到所有的runner都到起跑线，然后一起开跑~

				Random random = new Random();
				double time = random.nextDouble() + 9;
				System.out.println(name + ": " + time + "s");
			} catch (Exception e) {
			}
		}

	}
}
