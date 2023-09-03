package org.example.servlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;

@WebServlet("/calculate")
public class CalculatorServlet implements Servlet {
    /*
      Servlet 인터페이스: 서블릿 컨테이너(ex. 톰캣)와 상호작용하기 위한 메서드들(1.생명주기 관련 메서드들, 2.기타 메타정보 메서드들)
      Servlet - GenericServlet - HttpServlet 차례대로 구현
     */
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);

    @Override
    public void init(ServletConfig config) throws ServletException {
        log.info("init");
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        log.info("service");
    }

    @Override
    public void destroy() {

    }

    @Override
    public ServletConfig getServletConfig() {
        return null;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
}
