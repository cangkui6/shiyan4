package com.example.provider.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/provider")
public class ProviderController {

    private static final Logger logger = LoggerFactory.getLogger(ProviderController.class);
    
    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/hello")
    public String hello() {
        logger.info("收到 /provider/hello 请求，当前实例端口：{}", serverPort);
        return "Hello from Service Provider，实例端口：" + serverPort;
    }
    
    @GetMapping("/heavy-task/{taskId}")
    public String performHeavyTask(@PathVariable String taskId) {
        logger.info("收到繁重任务请求，taskId: {}，当前实例端口：{}", taskId, serverPort);
        try {
            // 模拟耗时操作
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
        }
        return String.format("任务 %s 已在Service Provider实例（端口：%s）处理完成", taskId, serverPort);
    }
    
    @GetMapping("/lb-test")
    public String loadBalanceTest() {
        logger.info("收到负载均衡测试请求，当前实例端口：{}", serverPort);
        return "负载均衡测试响应，来自Provider实例，端口：" + serverPort;
    }
}
