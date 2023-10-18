package dev.be.feign.feign.interceptor;

import feign.Request;
import feign.RequestInterceptor;
import feign.RequestTemplate;
import lombok.RequiredArgsConstructor;
import org.apache.commons.lang3.StringUtils;

import java.nio.charset.StandardCharsets;

@RequiredArgsConstructor(staticName = "of") // 이렇게 static method를 만들 수도 있다. - DemoFeignConfig에서 사용
public class DemoFeignInterceptor implements RequestInterceptor {
    // feign.RequestInterceptor를 implements하고 apply(RequestTemplate template)을 @Override해서 로직을 추가하면 알아서 interceptor가 구현됨
    
    @Override
    public void apply(RequestTemplate template) { // template이 갖고 있는 많은 필드들을 필요에 따라 직접 파싱해서 사용하면 된다.
        // 요청이 나가기 전 interceptor에서 요청을 체크해서, feign clien가 보내는 http 요청 메서드가 GET인지 POST인지에 따라 다른 로그를 출력하는 작업을 구현
        // get 요청일 경우
        if (template.method().equals(Request.HttpMethod.GET.name())) {
            System.out.println("[GET] [DemoFeignInterceptor] queries: " + template.queries() + "===> feign interceptor에서 찍은 로그");
            // template.queries() => template에서 해당 요청의 쿼리(쿼리 파라미터)들을 가져옴
            return;
        }

        // post 요청일 경우 - 인코딩된 String으로 불러올 수 있는 메서드를 사용
        String encodedRequestBody = StringUtils.toEncodedString(template.body(), StandardCharsets.UTF_8);
        // template.body() => template에서 해당 요청의 body를 가져옴
        System.out.println("[POST] [DemoFeignInterceptor] requestBody: " + encodedRequestBody + "===> feign interceptor에서 찍은 로그");

        // 필요하다면 interceptor에서 처리할 로직을 더 추가할 수 있음
        String convertRequestBody = encodedRequestBody;
        template.body(convertRequestBody);
    }
}
