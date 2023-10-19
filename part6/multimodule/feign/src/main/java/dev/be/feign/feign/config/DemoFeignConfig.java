package dev.be.feign.feign.config;

import dev.be.feign.feign.decoder.DemoFeignErrorDecoder;
import dev.be.feign.feign.interceptor.DemoFeignInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DemoFeignConfig {

    @Bean
    public DemoFeignInterceptor feignInterceptor() {
        return DemoFeignInterceptor.of();
    }

    @Bean
    // feign client가 통신하는 각 컴포넌트마다 포맷, 에러코드 등이 다를 수 있음
    // -> 각각의 client마다 다른 config를 사용하도록 함
    public DemoFeignErrorDecoder demoErrorDecoder() {
        return new DemoFeignErrorDecoder();
    }
}
