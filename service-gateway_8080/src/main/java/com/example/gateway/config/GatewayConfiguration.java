package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class GatewayConfiguration {

    /**
     * 使用Java代码方式配置路由
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // 配置API路由到provider服务
                .route("api-provider-route", r -> r.path("/api/provider/**")
                        .filters(f -> f
                            .rewritePath("/api/provider/(?<segment>.*)", "/provider/${segment}"))
                        .uri("lb://service-provider"))
                
                // 配置API路由到consumer服务
                .route("api-consumer-route", r -> r.path("/api/consumer/**")
                        .filters(f -> f
                            .rewritePath("/api/consumer/(?<segment>.*)", "/consumer/${segment}"))
                        .uri("lb://service-consumer"))
                .build();
    }
} 