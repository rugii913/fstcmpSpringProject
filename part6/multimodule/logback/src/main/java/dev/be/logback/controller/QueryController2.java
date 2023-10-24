package dev.be.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

// @Slf4j // 아래 직접 생성한 Logger의 변수 이름을 log2로 할 수도 있긴 하지만 - 한 파일에 Logger 타입이 2개 있을 필요가 없으므로 어노테이션 주석 처리
// 주석 해제하면 Slf4j에 빨간 줄 에러 뜸(필드 'log'이(가) 생성되지 않습니다. 이름이 같은 필드가 이미 있습니다)
// - 만약 여기서 @Slf4j의 topic = "SQL_LOG2"로 지정하고, LoggerFactory에서 가져온 Logger의 변수 이름을 log2로 할 경우, 서버도 제대로 뜨고, 로그도 잘 남겨지고, 같은 로그가 두 번 찍히지도 않지만, 굳이 같은 Logger를 두 번 지정할 필요는 없을 것
@RestController
public class QueryController2 {
    
    // QueryController1처럼 @Slf4j의 topic 속성을 통해, 사용할 로거를 지정하는 것이 아니라 LoggerFactory에서 직접 로거를 가져옴
    // org.slf4j 패키지의 LoggerFactory는 org.slf4j.Logger를 사용하므로, 받는 변수의 타입도 org.slf4j.Logger임
    public static final Logger log = LoggerFactory.getLogger("SQL_LOG2");

    @GetMapping("/query2")
    public String query2() {
        log.trace("log --> TRACE");
        log.debug("log --> DEBUG");
        log.info("log --> INFO");
        log.warn("log --> WARN");
        log.error("log --> ERROR");

        return "query2";
    }
}
