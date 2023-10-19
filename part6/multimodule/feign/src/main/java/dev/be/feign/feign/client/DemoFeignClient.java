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
    /*
    * Feign? declarative seb service client
    *  - Spring 프레임워크로 개발할 때 외부 component와 통신할 수 있는 여러 client 중 기존에 많이 사용했던 것은 RestTemplate
    *  - 최근에는 선언적으로 사용할 수 있는 feign client를 사용하여 더 간편하게 외부 component와 통신할 수 있도록 개발하는 추세
    *
    * feign client 통신 시 주요 설정
    * (1) 각 feign client의 connection timeout, read timeout 설정이 가능
    *   - application.yaml 설정을 통해 간편하게 처리 가능
    * (2) feign client 통신 관련 공통 처리해야할 부분이 있다면 interceptor를 재정의하여 처리 가능  ex.헤더 설정 작업을 각 메서드에서 하지 않고 인터셉터가 책임짐
    *   - Interceptor를 구현하는 클래스를 선언하고 apply(~)를 override, 빈으로 등록(보통 개별 feign client config)
    * (3) feign client 운영을 위한 log 설정  ex.API 응답 속도 확인 등
    *   - feign.Logger를 상속하는 클래스를 선언하고 각 메서드를 override, 빈으로 등록(보통 글로벌한 config)
    * (4) 외부 컴포넌트 응답이 정상 응답이 아닌 경우 error decoder로 핸들링 가능
    *   - ErrorDecoder를 구현하는 클래스를 선언하고 decode(~)를 override, 빈으로 등록(보통 개별 feign client config)
    * */

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
