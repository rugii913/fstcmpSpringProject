package dev.be.logback.controller;

import lombok.extern.slf4j.Slf4j;
import org.slf4j.MDC;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@Slf4j
@RestController
public class MdcController {
    /*
    * MDC(Mapped Diagnostic Context)? 멀티스레드 환경에서 로그를 남길 때 사용하는 개념
    *   - 전역변수 같은 것이라고 생각하면 됨
    *   - 각 스레드마다의 로그을 logback에 전달해주기 위한 것
    *   - 나중에 볼 참고 링크: https://logback.qos.ch/manual/mdc.html
    * + 기본적으로 자료구조가 Map과 같음 - 까보면 MDC가 갖고 있는 MDCAdapter 구현 객체(ex. LogbackMDCAdapter 객체)의 thread local인 map을 사용하는 것
    * */

    @GetMapping("/mdc")
    public String mdc() {
        MDC.put("job", "dev"); // 기본적으로 자료구조가 Map과 같음

        log.trace("log --> TRACE");
        log.debug("log --> DEBUG");
        log.info("log --> INFO");
        log.warn("log --> WARN");
        log.error("log --> ERROR");

        // 주의: 스레드 별로 MDC에 있는 값을 관리하는데, 다음에도 같은 스레드를 사용하게 될 경우, put한 값이 남아있으므로, put에는 clear가 항상 쌍으로 따라다녀야 함
        MDC.clear();

        return "mdc";
    }
}
