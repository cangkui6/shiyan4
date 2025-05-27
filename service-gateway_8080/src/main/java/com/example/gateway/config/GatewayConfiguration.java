package com.example.gateway.config;

import org.springframework.cloud.gateway.route.RouteLocator;
import org.springframework.cloud.gateway.route.builder.RouteLocatorBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Gateway路由配置
 * 
 * 注意：此配置与application.yml中的routes配置共存
 * 两种配置方式会合并，如果存在ID相同的路由，此Java配置会覆盖YAML配置
 */
@Configuration
public class GatewayConfiguration {

    /**
     * 使用Java代码方式配置路由
     * 这些路由与application.yml中定义的路由互补
     */
    @Bean
    public RouteLocator customRouteLocator(RouteLocatorBuilder builder) {
        return builder.routes()
                // API接口路由 - 以/api开头的路径
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