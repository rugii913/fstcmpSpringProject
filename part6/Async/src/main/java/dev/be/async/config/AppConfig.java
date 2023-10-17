package dev.be.async.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

@Configuration
public class AppConfig {

    // Spring 제공 ThreadPoolTaskExecutor를 이용하여 thread pool 정의
    @Bean(name = "defaultTaskExecutor", destroyMethod = "shutdown") // destroyMethod 옵션 - 의도치 않게 thread pool이 정리가 안 되는 상황을 방지
    public ThreadPoolTaskExecutor defaultTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(300);
//        executor.setKeepAliveSeconds(30);
//        executor.setQueueCapacity(500); // queueCapacity는 기본적으로 MAX로 설정되어 있음
        return executor;
    }

    @Bean(name = "messagingTaskExecutor", destroyMethod = "shutdown")
    public ThreadPoolTaskExecutor messagingTaskExecutor() {
        ThreadPoolTaskExecutor executor = new ThreadPoolTaskExecutor();
        executor.setCorePoolSize(200);
        executor.setMaxPoolSize(300);
        return executor;
    }
}
