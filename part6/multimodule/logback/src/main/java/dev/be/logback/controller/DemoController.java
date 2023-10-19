package dev.be.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class DemoController {

    @GetMapping("/demo")
    public String demo() {
        log.trace("log --> TRACE");
        log.debug("log --> DEBUG");
        log.info("log --> INFO");
        log.warn("log --> WARN");
        log.error("log --> ERROR");
        // 현재 appender에서 threshold filter를 INFO 레벨로 설정했기 때문에 INFO 이상 레벨만 출력된다.

        return "demo";
    }
}
