package dev.be.moduleapi;

import dev.be.modulecommon.service.CommonDemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Import;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication(scanBasePackages = {"dev.be.moduleapi", "dev.be.modulecommon"})
// @Import(CommonDemoService.class)
@EntityScan("dev.be.modulecommon.domain")
@EnableJpaRepositories(basePackages = "dev.be.modulecommon.repositories")
// 간단하게 참고 - https://yousrain.tistory.com/37 - 외부 모듈의 클래스는 JPA의 스캔 대상이 아니므로, JPA 스캔 대상으로 명시
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }

}
