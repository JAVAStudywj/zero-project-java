package com.zero.gateway.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
public class GlobalCorsConfig {

    /**
     * 跨域处理
     * @return
     */
    private CorsConfiguration buildConfig() {
        //1 添加 cors 的配置信息
        CorsConfiguration config = new CorsConfiguration();
        //1 允许的域，不允许写* 否则cookie就无法使用
        config.addAllowedOrigin("*"); // 不建议使用，会导致cookie无法使用
        config.addAllowedOrigin("http://api.dilvis.com");
        config.addAllowedOrigin("http://www.dilvis.com");
        config.addAllowedOrigin("http://eureka.dilvis.com");
        //2 允许的头信息
        config.addAllowedHeader("*"); // 2允许任何头
        //3 允许的请求方式 post、get等）
        config.addAllowedMethod("OPTIONS");
        config.addAllowedMethod("HEAD");
        config.addAllowedMethod("GET");
        config.addAllowedMethod("POST");
        config.addAllowedMethod("DELETE");
        config.addAllowedMethod("PATCH");
        //4 是否发送cookie信息
        config.setAllowCredentials(true);
        //5 有效时长
        config.setMaxAge(86400L);
        return config;
    }

    @Bean
    public CorsFilter corsFilter() {
        //5 添加映射路径 我们拦截一切请求
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", buildConfig()); // 4 映射路径
        return new CorsFilter(source);
    }
}
