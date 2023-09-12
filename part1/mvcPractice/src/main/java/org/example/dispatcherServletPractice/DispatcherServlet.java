package org.example.dispatcherServletPractice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet("/") // 어떤 경로를 입력하더라도 이 DispatcherServlet이 호출
// 왜 그런지는 spec을 참고해야될 것 같다... // 버전은 다르겠지만 최신 버전인 https://jakarta.ee/specifications/servlet/5.0/jakarta-servlet-spec-5.0을 참고해도 크게 상관 없을 듯
// 12.1. Use of URL Paths 12.2. Specification of Mappings + 3.5. Request Path Elements 정도 참고해보면 될 것 같다.
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);

    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        log.info("DispatcherServlet#service");
    }
}
