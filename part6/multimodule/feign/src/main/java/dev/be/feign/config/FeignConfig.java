package dev.be.feign.config;

import dev.be.feign.feign.logger.FeignCustomLogger;
import feign.Logger;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FeignConfig {

    @Bean
    // log의 경우, 글로벌하게 모든 client가 사용하므로, 공통된 포맷으로 관리해야하는 설정이라고 생각하면 된다. -> DemoFeignConfig가 아니라 FeignConfig에서 빈으로 등록
    public Logger feignLogger() {
        return new FeignCustomLogger();
    }
}
