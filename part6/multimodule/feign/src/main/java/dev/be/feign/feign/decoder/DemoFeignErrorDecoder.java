package dev.be.feign.feign.decoder;

import feign.Response;
import feign.codec.ErrorDecoder;
import org.springframework.http.HttpStatus;

public class DemoFeignErrorDecoder implements ErrorDecoder {

    private final ErrorDecoder errorDecoder = new Default(); // Default: ErrorDecoder 인터페이스의 내부 static 클래스

    @Override
    public Exception decode(String methodKey, Response response) {
        // status 값으로 분기
        HttpStatus httpStatus = HttpStatus.resolve(response.status());

        if (httpStatus == HttpStatus.NOT_FOUND) {
            System.out.println("[DemoFeignErrorDecoder] Http Status = " + httpStatus);
            throw new RuntimeException(String.format("[RuntimeException] Http Status is %s", httpStatus));
            // 여기서는 단순하게 RuntimeException으로 전환하기만 했지만, 실무에서는 status에 따라 적절하게 핸들링하여 예외를 처리함
        }
        /*
        * 외부 컴포넌트와 통신 시 위처럼 명시적으로 몇가지 예외 코드를 미리 명시해서
        * custom한 예외로 래핑한다든지 적절하게 핸들링하여 처리할 수 있다.
        * */

        /*
        * 모든 예외를 다 핸들링할 수는 없으므로,
        * 위에서 핸들링 명시하지 않은 에러들은 어느 정도 적절히 처리하도록 default로 구현해놓은
        * feign.codec.ErrorDecoder의 decode(~)를 사용한다.
        * */
        return errorDecoder.decode(methodKey, response);
    }
}
