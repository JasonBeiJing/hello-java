package com.hello.java.concurrent.countdownlatch;

import java.util.concurrent.CountDownLatch;

public abstract class AbstractHealthChecker implements Runnable {

	private CountDownLatch _latch;
	private String _serviceName;
	private boolean _serviceUp;

	// Get latch object in constructor so that after completing the task, thread can
	// countDown() the latch
	public AbstractHealthChecker(String serviceName, CountDownLatch latch) {
		super();
		this._latch = latch;
		this._serviceName = serviceName;
		this._serviceUp = false;
	}

	@Override
	public void run() {
		try {
			verifyService();
			_serviceUp = true;
		} catch (Throwable t) {
			t.printStackTrace();
			_serviceUp = false;
		} finally {
			if (_latch != null) {
				_latch.countDown();
			}
		}
	}

	public String getServiceName() {
		return _serviceName;
	}

	public boolean isServiceUp() {
		return _serviceUp;
	}

	public abstract void verifyService();

}
