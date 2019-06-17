package com.hello.java.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TryCompletableFuture1 {
	private static final ExecutorService executor = Executors.newCachedThreadPool();

	public static void main(String[] args) throws InterruptedException, ExecutionException {
		//void
		CompletableFuture.runAsync(() -> {
			System.out.println("=== CompletableFuture.runAsync() 【default thread pool】 finished === is Daemon : " + Thread.currentThread().isDaemon());
		});
		CompletableFuture.runAsync(() -> {
			System.out.println("=== CompletableFuture.runAsync() 【specific thread pool】 finished === is Daemon : " + Thread.currentThread().isDaemon());
		}, executor);
		
		//T
		CompletableFuture.supplyAsync(() -> 678L).whenComplete((result, exception) -> {
			System.err.println(result + " ==【default thread pool】== " + exception + " === is Daemon : " + Thread.currentThread().isDaemon());
		});
		CompletableFuture.supplyAsync(() -> "hahaha", executor).whenComplete((result, exception) -> {
			System.err.println(result + " ==【specific thread pool】== " + exception + " === is Daemon : " + Thread.currentThread().isDaemon());
		});
		
		//exit
		TimeUnit.SECONDS.sleep(2);
		executor.shutdownNow();
	}
}
