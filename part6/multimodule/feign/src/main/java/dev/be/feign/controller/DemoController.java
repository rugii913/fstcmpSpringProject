package dev.be.feign.controller;

import dev.be.feign.service.DemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class DemoController {

    private final DemoService demoService;

    @GetMapping("/get")
    public String getController() {
        return demoService.get();
    }

    /*
    * 현재 요청-응답 흐름 정리
    * demoController로 요청이 들어옴 -> controller가 demoService 호출
    * -> demoService에서는 demoFeignClient의 callGet(~) 메서드 호출 - CustomHeader, name, age를 인자로 담음
    * -> demoFeignClient에서는 [@FeignClient의 url 속성으로 정의된 prefix 값 + GetMapping의 value 값을 붙인 url]로 GET 요청을 날림
    *    - 이 때 @RequestHeader의 key로 "CustomHeaderName", value로 service에서 넘어온 customHeader 값, 
    *      @RequestParam의 key로 "name", value로 service에서 넘어온 name 값, ... age도 마찬가지, ... 
    *      을 보냄
    * ///// ** 같은 8080 port의 TargetController에서 처리하는 것으로 구현되어 있음 - 위 FeignClient의 요청을 받아서 처리 후, BaseResponseInfo 타입으로 반환
    * ///// **** 이 때, demoFeignClient의 헤더 중 CustomHeaderName이라는 key 값을 가진 헤더의 value를 가져와서 응답의 body로 넣어준다.
    * ///// **** 즉, 지금 header와 param으로 정보를 받고서는, body로 정보를 내려주고 있는 상황임
    * -> (/target-server에서 응답을 보낸 후) demoService에서 demoFeignClient의 callGet(~) 메서드 반환 타입 지정된 대로 ResponseEntity<BaseResponseInfo> 타입으로 응답을 받고,
    *                                     response 객체에 저장
    *                                     - response의 body에 있는 내용들을 분해해서 콘솔창에 출력
    * */
}
