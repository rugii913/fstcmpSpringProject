package dev.be.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j(topic = "SQL_LOG1")
// @Slf4j의 topic() 메서드 - 사용할 로거의 이름 지정
@RestController
public class QueryController1 {

    @GetMapping("/query1")
    public String query1() {
        log.trace("log --> TRACE");
        log.debug("log --> DEBUG");
        log.info("log --> INFO");
        log.warn("log --> WARN");
        log.error("log --> ERROR");

        return "query1";
    }
}
