package com.hello.java.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

public class TryCompletableFuture2 {

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		CompletableFuture<Integer> f = CompletableFuture
				.supplyAsync(() -> doSomething(1))
				.whenComplete((result, exception) -> {System.err.println(result + " ==【default thread pool】== " + exception);})
				.exceptionally(m -> {System.err.println("Exception is: ===> " + m.getClass().getCanonicalName()); return -2; })
				;
		
		System.out.println(f.get() + " ===> " + f.isCompletedExceptionally());
		//exit 
		TimeUnit.SECONDS.sleep(2);
	}
	
	private static int doSomething(int in){
		try {
			if(in < 0) {
				throw new RuntimeException();
			}
			TimeUnit.SECONDS.sleep(in);
			return in;
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}
}
