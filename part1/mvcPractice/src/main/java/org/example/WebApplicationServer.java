package org.example;

import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;
import org.example.servlet.CalculatorServlet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;

public class WebApplicationServer {

    public static final Logger log = LoggerFactory.getLogger(WebApplicationServer.class);

    public static void main(String[] args) throws LifecycleException {
        // 내장 톰캣(build.gradle에서 의존성)
        String webappDirLocation = "webapps/";
        // 프로젝트 구조 > 프로젝트 설정 > 모듈 > main > 경로 > 컴파일러 출력 경로를
        // 기존 C:\fastcampusSpringProject\part1\mvcPractice\out\production\classes에서
        // mvcPractice 밑에 webapps\WEB-INF\classes 폴더를 생성한 뒤
        // 컴파일러 출력 경로를
        // C:\fastcampusSpringProject\part1\mvcPractice\mvcPractice\webapps\WEB-INF\classes로 변경
        // ====> 서버를 실행하면 컴파일된 출력 결과가 해당 경로로 들어감
        // (혼자) (아마도?? 확인 필요)
        // 프로젝트 구조 > 프로젝트 설정 > 프로젝트 > 컴파일러 출력도
        // C:\fastcampusSpringProject\part1\mvcPractice\webapps\WEB-INF\classes로 변경

        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080); // 포트 번호 설정

        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        // => localhost:8080으로 들어왔을 때, webappDirLocation(=="webapp")을 바라보도록할 것
        // (Tomcat 클래스 코드 보면) contextPath ""가 root context이고, docBase는 context에 대한 base directory임
        // ===> 이 곳을 웹어플리케이션으로 추가하고, 이 위치에서 톰캣이 파일들을 찾아서 실행
        // (?) addWebapp(~) 및 getAbsolutePath(~), File 생성자 pathname 매개변수 관련 설명 더 읽어볼 것
        log.info("configuring app with basedir: {}", new File(webappDirLocation).getAbsolutePath());

        // 일단 강의 코드와는 상관 없이 context에 servlet 추가해서 /calculate 경로로 request 들어올 수 있게는 만듦
        // 정확히 어떤 게 문제인지는 더 알아봐야할 듯
        // 참고: https://lordofkangs.tistory.com/295
        // 참고?? https://codevang.tistory.com/194
        // 어노테이션으로 연결하려면 요청 url에 프로젝트 이름을 넣어야하는가? /mvcPractice/calculate
        // 이렇게 해봐도 안 됨
        /*
        // DispatcherServlet 관련 부분 시작하며 주석 처리
        Context context = tomcat.addContext("/", "");
        tomcat.addServlet("/", "calculatorServlet", new CalculatorServlet());
        context.addServletMappingDecoded("/calculate", "calculatorServlet");
        */

        tomcat.start();
        tomcat.getServer().await();
    }
}