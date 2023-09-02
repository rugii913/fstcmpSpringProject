package org.example.wasPractice;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

/*
 * HttpRequest 객체
 * * http 메시지를 readLine()으로 읽고 나서, HttpRequest라는 객체로 만든다면, 그 내부에는 무엇이 있을까?
 * - requestLine (ex. GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1)
 *  - httpMethod
 *  - path
 *  - queryString
 *  - protocol/version(이 부분은 사용하지 않을 것)
 * - header
 * - body
 * * 위 중에서 계산기 어플에는 requestLine만 필요할 것
 */
public class RequestLineTest {

    @Test
    void create() {
        RequestLine requestLine = new RequestLine("GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1");
        assertThat(requestLine).isNotNull();
        assertThat(requestLine).isEqualTo(new RequestLine("GET", "/calculate", "operand1=11&operator=*&operand2=55"));
    }
}
