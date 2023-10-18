package dev.be.feign.feign.logger;

import feign.Logger;
import feign.Request;
import feign.Response;
import feign.Util;
import lombok.RequiredArgsConstructor;

import java.io.IOException;

import static feign.Util.*;

@RequiredArgsConstructor
public class FeignCustomLogger extends Logger { // feign.Logger를 상속받아야 함
    // elapsedTime에 대한 핸들링은 중요함, API 호출 시간이 오래 걸릴 경우 이를 알리는 로그를 남기기 위한 필드들
    private static final int DEFAULT_SLOW_API_TIME = 3_000;
    private static final String SLOW_API_NOTICE = "Slow API";

    @Override
    // feign.Logger의 유일한 추상 메서드 - 이건 꼭 override 해야함
    protected void log(String configKey, String format, Object... args) {
        // log를 어떤 형식으로 남길지 정해줌 - 아래 logAndRebufferResponse 같은 곳에서 사용
        // System.out.println(String.format(methodTag(configKey) + format, args));
        System.out.printf(methodTag(configKey) + format + "%n", args); // String methodTag(String configKey)는 상속하고 있는 Logger의 static method
    }

    @Override
    // request 핸들링
    protected void logRequest(String configKey, Level logLevel, Request request) {
        // super.logRequest(configKey, logLevel, request);
        System.out.println("[logRequest]: " + request);
    }

    @Override
    // 이 메서드가 가장 중요
    // response + a를 핸들링
    // 어떻게 구현할지 참고하려면 이 메서드의 다른 구현을 살펴볼 것  ex.Logger에 있는 logAndRebufferResponse(~) 메서드
    // 회사에서는 원하는 내용으로 custom하게 구현
    protected Response logAndRebufferResponse(String configKey, Level logLevel, Response response, long elapsedTime) throws IOException {
        // return super.logAndRebufferResponse(configKey, logLevel, response, elapsedTime); // super.~ 호출하지 않고 메서드 구현 내용 복붙함
        String protocolVersion = resolveProtocolVersion(response.protocolVersion()); // Logger의 static method
        String reason =
                response.reason() != null && logLevel.compareTo(Level.NONE) > 0 ? " " + response.reason()
                        : "";
        int status = response.status();
        log(configKey, "<--- %s %s%s (%sms)", protocolVersion, status, reason, elapsedTime); // 위에서 @Override한 method // reason은 에러 등, elapsedTime은 파라미터로 받은 응답 시간 - 요청 시간
        if (logLevel.ordinal() >= Level.HEADERS.ordinal()) {

            for (String field : response.headers().keySet()) { // response의 header를 for 돌면서 출력
                if (shouldLogResponseHeader(field)) {
                    for (String value : valuesOrEmpty(response.headers(), field)) {
                        log(configKey, "%s: %s", field, value);
                    }
                }
            }

            int bodyLength = 0;
            if (response.body() != null && !(status == 204 || status == 205)) {
                // HTTP 204 No Content "...response MUST NOT include a message-body"
                // HTTP 205 Reset Content "...response MUST NOT include an entity"
                if (logLevel.ordinal() >= Level.FULL.ordinal()) {
                    log(configKey, ""); // CRLF
                }
                byte[] bodyData = Util.toByteArray(response.body().asInputStream());
                bodyLength = bodyData.length;
                if (logLevel.ordinal() >= Level.FULL.ordinal() && bodyLength > 0) {
                    log(configKey, "%s", decodeOrDefault(bodyData, UTF_8, "Binary data"));
                }
                log(configKey, "<--- END HTTP (%s-byte body)", bodyLength); // END HTTP ~로 마무리

                // 디버그 모드에서 elapsedTime 변수값을 인위적으로 조정해서 실험해볼 수 있다.(표현식 평가 Alt+F8 혹은 오른쪽에 뜨는 변수명 클릭하고 값 설정 F2)
                if (elapsedTime > DEFAULT_SLOW_API_TIME) {
                    log(configKey, "[%s] elapsedTime: %sms", SLOW_API_NOTICE, elapsedTime);
                    // 이후 개발자에게 메일, 문자 알람 등을 대응 장치 로직을 추가할 수 있다.
                }

                return response.toBuilder().body(bodyData).build();
            } else {
                log(configKey, "<--- END HTTP (%s-byte body)", bodyLength);
            }
        }
        return response;
    }
}
