package org.example.wasPractice;

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

                    // http 메시지를 눈으로 확인하기 위한 부분
                    String line;
                    while ((line = br.readLine()) != "") {
                        System.out.println(line);
                    }
                }
            }
        }
    }
}
