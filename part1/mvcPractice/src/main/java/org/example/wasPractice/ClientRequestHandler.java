package org.example.wasPractice;

import org.example.oopPractice.calculator.domain.Calculator;
import org.example.oopPractice.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class ClientRequestHandler implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(ClientRequestHandler.class);
    private final Socket clientSocket;
    public ClientRequestHandler(Socket clientSocket) {
        this.clientSocket = clientSocket;
    }

    /*
     * Step1- 사용자 요청을 메인 Thread가 처리하도록 한다. (완료)
     *  !!다른 요청이 들어와도 처리 중인 요청이 끝날 때까지 기다려야하는 문제점
     * Step2- 사용자 요청이 들어올 때마다 Thread를 새로 생성해서 사용자 요청을 처리하도록 한다.
     *  - client의 요청별로 별도의 스레드 생성 -> 스레드 별로 InputStream, OutputStream 얻기 -> 아래 작업 수행 -> response를 클라이언트에 전달
     *  !!thread는 생성될 때마다 독립적인 stack 메모리 공간을 할당받음 - 메모리 할당 작업은 비싼 작업 - 성능 문제
     *  !!CPU 컨텍스트 스위치 문제, CPU나 메모리 사용량 증가, 서버 다운될 수 있음
     * Step3- Thread Pool을 적용해 안정적인 서비스가 가능하도록 한다.
     */
    @Override
    public void run() {
        logger.info("[ClientRequestHandler] new client {} started", Thread.currentThread().getName());

        // clientSocket 객체를 사용해서, InputStream, OutputStream 열기
        try (InputStream in = clientSocket.getInputStream(); OutputStream out = clientSocket.getOutputStream()) {
            // 하고 싶은 것: line by line으로 읽기
            // InputStream을 InputStreamReader로 바꿈(charset UTF8 형식으로)
            // -> 내부에서 StreamDecoder의 static 메서드로 charset 정보 통해서 StreamDecoder 타입으로 갖고 있음
            // -> BufferedReader로 감싸서 line by line으로 읽을 준비하기
            BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));
            // OutputStream도 DataOutputStream으로 감싸줌
            DataOutputStream dos = new DataOutputStream(out);

            HttpRequest httpRequest = new HttpRequest(br);

            // GET /calculate?operand1=11&operator=*&operand2=55 HTTP/1.1
            if (httpRequest.isGetRequest() && httpRequest.matchPath("/calculate")) {
                QueryStrings queryStrings = httpRequest.getQueryStrings();

                int operand1 = Integer.parseInt(queryStrings.getValue("operand1"));
                String operator = queryStrings.getValue("operator");
                int operand2 = Integer.parseInt(queryStrings.getValue("operand2"));

                int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));
                byte[] body = String.valueOf(result).getBytes();

                synchronized (this) { // 간이하게 작성했는데, 심지어 제대로 처리 안 됨 // Step2까지 작성하고 나면 여러 스레드 생성되는 것은 확인할 수 있음
                    // (참고)
                    // - IllegalMonitorStateException
                    // https://jojonari.tistory.com/entry/%EC%9E%90%EB%B0%94java-IllegalMonitorStateException
                    // - [Java] 자바 동기화 synchronized, wait(), notify()
                    // https://abcdefgh123123.tistory.com/418
                    try {
                        wait(5000);
                    } catch (InterruptedException e) {
                        throw new RuntimeException(e);
                    }
                }

                HttpResponse response = new HttpResponse(dos);
                response.response200Header("application/json", body.length);
                response.responseBody(body);
            }

            // http 메시지를 눈으로 확인하기 위한 부분
                    /*
                    String line;
                    while ((line = br.readLine()) != "") {
                        System.out.println(line);
                    }
                    */
            /**
             * 스프링을 이용해서 웹개발을 할 때,
             * 위처럼 http 메시지를 파싱을 해서 요청을 판단하고,
             * 스프링에 요청을 보내는 작업을 하는 것이 톰캣(기본값)
             * 지금 톰캣처럼 was 역할을 해주는 것을 만들어보는 것임
             * - http 프로토콜을 어떻게 파싱하고, 어떻게 처리할 것인지
             */
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

}
