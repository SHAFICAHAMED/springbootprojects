package com.example.asyncSB.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class AsyncService {

    @Async
    public void performTask() {
        System.out.println("Task started on thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000); // Simulate a long-running task
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Task completed");
    }

    @Async
    public CompletableFuture<String> fetchData() {
        System.out.println("Fetching data on thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return CompletableFuture.completedFuture("Data fetched successfully");
    }

}
