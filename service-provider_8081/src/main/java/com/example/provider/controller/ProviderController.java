package com.example.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);

    @GetMapping("/hello")
    public String hello() {
        logger.info("8081，收到 /provider/hello 请求");
        return "Hello from Service Provider_8081!";
    }
    
    @GetMapping("/heavy-task/{taskId}")
    public String performHeavyTask(@PathVariable String taskId) {
        logger.info("8081，收到繁重任务请求，taskId: {}", taskId);
        try {
            // 模拟耗时操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return String.format("任务 %s 已在Service Provider_8081处理完成", taskId);
    }
}
