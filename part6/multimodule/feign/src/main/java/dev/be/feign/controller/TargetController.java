package dev.be.feign.controller;

import dev.be.feign.common.dto.BaseRequestInfo;
import dev.be.feign.common.dto.BaseResponseInfo;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/target_server")
public class TargetController { // 같은 컴퓨터 8080 포트 내에서 Feign이 이곳을 호출하는 형태로 연습해보는 것

    @GetMapping("/get")
    public BaseResponseInfo demoGet(@RequestHeader("CustomHeaderName") String header,
                                    @RequestParam("name") String name,
                                    @RequestParam("age") Long age) {
        return BaseResponseInfo.builder()
                .header(header.concat(" =>=>=> feign client 요청 header 중 \"CustomHeaderName\"이라는 key를 가진 header의 value이다."))
                .name(name.concat(" =>=>=> feign client 요청 parameter 중 name 값이다."))
                .age(age * 100)
                .build();
    }

    @PostMapping("/post")
    public BaseResponseInfo demoPost(@RequestHeader("CustomHeaderName") String header,
                                    @RequestBody BaseRequestInfo body) {
        return BaseResponseInfo.builder()
                .header(header.concat(" =>=>=> feign client 요청 body 중 \"CustomHeaderName\"이라는 key를 가진 header의 value이다."))
                .name(body.getName().concat(" =>=>=> feign client 요청 body 중 name 값이다."))
                .age(body.getAge() * 100)
                .build();
    }
}
