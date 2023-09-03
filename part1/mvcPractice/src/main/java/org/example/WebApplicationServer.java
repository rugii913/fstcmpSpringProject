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
        String webappDirLocation = "webapp";
        Tomcat tomcat = new Tomcat();
        tomcat.setPort(8080);

        tomcat.addWebapp("", new File(webappDirLocation).getAbsolutePath());
        log.info("configuring app with basedir: {}", new File(webappDirLocation).getAbsolutePath());

        // 일단 강의 코드와는 상관 없이 context에 servlet 추가해서 /calculate 경로로 request 들어올 수 있게는 만듦
        // 정확히 어떤 게 문제인지는 더 알아봐야할 듯
        // 참고: https://lordofkangs.tistory.com/295
        // 참고?? https://codevang.tistory.com/194
        // 어노테이션으로 연결하려면 요청 url에 프로젝트 이름을 넣어야하는가? /mvcPractice/calculate
        // 이렇게 해봐도 안 됨
        Context context = tomcat.addContext("/", "");
        tomcat.addServlet("/", "calculatorServlet", new CalculatorServlet());
        context.addServletMappingDecoded("/calculate", "calculatorServlet");


        tomcat.start();
        tomcat.getServer().await();
    }
}