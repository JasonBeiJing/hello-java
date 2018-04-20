package com.hello.java.countdownlatch;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ApplicationStartupUtil {

	private ApplicationStartupUtil() {}

	public static List<AbstractHealthChecker> getServices(CountDownLatch _latch, ServiceTypeEnum ... type){
		List<AbstractHealthChecker> _services = new ArrayList<>();
		for(ServiceTypeEnum t:type) {		
			if(t==ServiceTypeEnum.CACHE) {
				_services.add(new CacheHealthChecker(_latch));	
			}else if(t==ServiceTypeEnum.DB) {
				_services.add(new DatabaseHealthChecker(_latch));	
			}else if(t==ServiceTypeEnum.NETWORK) {
				_services.add(new NetworkHealthChecker(_latch));	
			}else {
				throw new IllegalArgumentException(t.name());
			}
		}
		return _services;
	}
	
	public static boolean checkExternalServices(ServiceTypeEnum ... type) throws Exception {
		CountDownLatch _latch = new CountDownLatch(type.length);

		List<AbstractHealthChecker> _services = getServices(_latch, type);

		ExecutorService executor = Executors.newFixedThreadPool(_services.size());
		for (final AbstractHealthChecker v : _services) {
			executor.execute(v);
		}
		
		_latch.await();
		executor.shutdownNow();

		// Services are file and now proceed startup
		for (final AbstractHealthChecker v : _services) {
			if (!v.isServiceUp()) {
				return false;
			}
		}
		return true;
	}
	
	public static void main(String[] args) throws Exception {
		System.err.println("Final status ======> " + checkExternalServices(ServiceTypeEnum.values()));
	}
}