package com.example.asyncSB.cntroller;

import com.example.asyncSB.service.AsyncService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletableFuture;

@RestController
@RequestMapping("/api")
public class AsyncController {

    private final AsyncService asyncService;

    public AsyncController(AsyncService asyncService) {
        this.asyncService = asyncService;
    }

    @GetMapping("/start-task")
    public String startTask() {
        asyncService.performTask();
        return "Task started";
    }

    @GetMapping("/fetch-data")
    public CompletableFuture<String> fetchData() {
        return asyncService.fetchData();
    }
}
