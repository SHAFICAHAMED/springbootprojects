package com.example.asyncSB.service;

import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Service
public class ScheduledService {

    @Scheduled(fixedRate = 10000)
    @Async
    public void executeScheduledTask() {
        System.out.println("Scheduled task started on thread: " + Thread.currentThread().getName());
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println("Scheduled task completed");
    }
}
