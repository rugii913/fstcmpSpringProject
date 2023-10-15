package dev.be.moduleapi;

import dev.be.modulecommon.service.CommonDemoService;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Import;

@SpringBootApplication(scanBasePackages = {"dev.be.moduleapi", "dev.be.modulecommon"})
// @Import(CommonDemoService.class)
public class ModuleApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(ModuleApiApplication.class, args);
    }

}
