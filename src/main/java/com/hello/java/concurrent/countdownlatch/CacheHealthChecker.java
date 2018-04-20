package com.hello.java.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class CacheHealthChecker extends AbstractHealthChecker {
	public CacheHealthChecker(CountDownLatch latch) {
		super("Cache Service", latch);
	}

	@Override
	public void verifyService() {
		System.out.println("Checking " + this.getServiceName());
		try {
			TimeUnit.SECONDS.sleep(3);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}
}