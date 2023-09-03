package org.example.wasPractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CustomWebApplicationServer {

    private final int port;
    // 다양한 API 중 newFixedThreadPool 사용하여 간단하게 만들어보기
    private final ExecutorService executorService = Executors.newFixedThreadPool(10);
    public static final Logger logger = LoggerFactory.getLogger(CustomWebApplicationServer.class);

    public CustomWebApplicationServer(int port) {
        this.port = port;
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
    public void start() throws IOException {
        try (ServerSocket serverSocket = new ServerSocket(port)) {
            logger.info("[CustomWebApplicationServer] started {} port.", port);

            Socket clientSocket;
            logger.info("[CustomWebApplicationServer] waiting for client");

            while ((clientSocket = serverSocket.accept()) != null) {
                logger.info("[CustomWebApplicationServer] client connected!");

                /*
                // 클라이언트가 연결될 때마다, Runnable 타입인 ClientRequestHandler를 생성
                // -> 그 Runnable을 가진 Thread를 생성
                // -> 생성된 Thread를 start() -> Runnable의 run() 작동
                // ==> main thread가 block 되는 게 아니라 별도의 thread를 생성해서 처리하기 때문에 다음 요청도 처리 가능
                // new Thread(new ClientRequestHandler(clientSocket)).start();
                 */

                // 어플리케이션이 시작되면 CustomWebApplicationServer 생성 - 필드로 10개의 스레드를 갖고 있는 ExecutorService를 새로 생성
                // 요청이 들어오면(clientSocket != null) excutorService에 execute(Runnable)을 요청
                //  - 여기서 Runnable은 ClientRequestHandler - 현재 갖고 있는 not null인 clientSocket을 함께 전달
                // Runnable(ClientRequestHandler)이 run()
                executorService.execute(new ClientRequestHandler(clientSocket));
            }
        }
    }
}
