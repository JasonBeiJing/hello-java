package com.hello.java.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DatabaseHealthChecker extends AbstractHealthChecker {
	public DatabaseHealthChecker(CountDownLatch latch) {
		super("Database Service", latch);
	}

	@Override
	public void verifyService() {
		System.out.println("Checking " + this.getServiceName());
		try {
			TimeUnit.SECONDS.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		System.out.println(this.getServiceName() + " is UP");
	}
}