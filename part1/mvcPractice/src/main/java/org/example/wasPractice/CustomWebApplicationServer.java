package org.example.wasPractice;

import org.example.oopPractice.calculator.domain.Calculator;
import org.example.oopPractice.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.nio.charset.StandardCharsets;

public class CustomWebApplicationServer {

    private final int port;
    public static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
    }

    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");

                /**
                 * Stpe1 - 사용자 요청을 메인 Thread가 처리하도록 한다.
                 */

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
                }
            }
        }
    }
}
