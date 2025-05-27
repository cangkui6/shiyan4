package com.example.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.reactive.CorsWebFilter;
import org.springframework.web.cors.reactive.UrlBasedCorsConfigurationSource;

import java.util.Arrays;

/**
 * 全局跨域配置
 * 允许前端应用跨域访问Gateway的API
 */
@Configuration
public class CorsConfiguration {

    @Bean
    public CorsWebFilter corsWebFilter() {
        org.springframework.web.cors.CorsConfiguration corsConfig = new org.springframework.web.cors.CorsConfiguration();
        
        // 允许的域，指定允许的具体域名
        corsConfig.addAllowedOriginPattern("*");  // 使用pattern而不是origin，解决与allowCredentials的冲突
        
        // 允许的请求方法
        corsConfig.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS"));
        
        // 允许的请求头
        corsConfig.addAllowedHeader("*");
        
        // 是否允许携带凭证（如Cookie）
        corsConfig.setAllowCredentials(true);
        
        // 预检请求的有效期，单位为秒
        corsConfig.setMaxAge(3600L);
        
        // 允许暴露的响应头
        corsConfig.addExposedHeader("Authorization");
        corsConfig.addExposedHeader("X-Auth-Token");
        
        // 创建URL映射
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        
        // 对所有路径应用这些CORS配置
        source.registerCorsConfiguration("/**", corsConfig);
        
        return new CorsWebFilter(source);
    }
} 