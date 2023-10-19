package dev.be.feign.feign.client;

import dev.be.feign.common.dto.BaseRequestInfo;
import dev.be.feign.common.dto.BaseResponseInfo;
import dev.be.feign.feign.config.DemoFeignConfig;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(
        name = "demo-client",
        url = "${feign.url.prefix}", // 요청을 보내고자하는 target 서버의 url 값 - 보통 하드 코딩하기보다는 property 값으로 관리한다 - 여기서는 application.yaml에 정의해두었음
        // configuration = DemoFeignClient.class // -> 실수로 이렇게 잘못 지정해두었는데, ... Failed to instantiate [dev.be.feign.feign.client.DemoFeignClient]: Specified class is an interface 에러 발생함
        // 아예 configuration 속성을 없앴을 때는 동작함, DemoFeignConfig가 아무 내용 없는 깡통인 것 같은데도, 어쨌든 이 부분이 잘못 지정되면 동작하지 않음
        configuration = DemoFeignConfig.class
)
public interface DemoFeignClient { // Feign: 선언적으로 사용할 수 있는 client -> interface를 사용함

    @GetMapping("/get") // -> 위 url 값과 합쳐져서 http://localhost:8080/target_server/get으로 요청을 보낸다.
    ResponseEntity<BaseResponseInfo> callGet(@RequestHeader("CustomHeaderName") String customHeader,
                                             @RequestParam("name") String name,
                                             @RequestParam("age") Long age);

    @PostMapping("/post")
    ResponseEntity<BaseResponseInfo> callPost(@RequestHeader("CustomHeaderName") String customHeader,
                                              @RequestBody BaseRequestInfo baseRequestInfo);

    @GetMapping("/error")
    ResponseEntity<BaseResponseInfo> callErrorDecoder();
}
