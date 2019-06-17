package com.hello.java.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TryCompletableFuture {
	private static final ExecutorService executor = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//void
		CompletableFuture.runAsync(() -> {
			System.out.println("=== CompletableFuture.runAsync() [default thread pool] finished === ");
		});
		CompletableFuture.runAsync(() -> {
			System.out.println("=== CompletableFuture.runAsync() [specific thread pool] finished === ");
		}, executor);
		
		//T
		CompletableFuture.supplyAsync(() -> 678L).whenComplete((result, exception) -> {
			System.err.println(result + " === " + exception);
		});
		CompletableFuture.supplyAsync(() -> "hahaha", executor).whenComplete((result, exception) -> {
			System.err.println(result + " === " + exception);
		});
		
		//exit
		TimeUnit.SECONDS.sleep(3);
		executor.shutdownNow();
	}
}
