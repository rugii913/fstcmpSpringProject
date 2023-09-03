package org.example.servlet;

import org.example.oopPractice.calculator.domain.Calculator;
import org.example.oopPractice.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculatorServlet extends GenericServlet {
    /*
      Servlet 인터페이스: 서블릿 컨테이너(ex. 톰캣)와 상호작용하기 위한 메서드들(1.생명주기 관련 메서드들, 2.기타 메타정보 메서드들)
      Servlet - GenericServlet - HttpServlet 차례대로 구현
     */
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    // private ServletConfig servletConfig;
    // GenericServlet의 경우, service(~)만 구현하는 것도 가능하다.

    @Override
    public void service(ServletRequest request, ServletResponse response) throws ServletException, IOException {
        log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

    /*
    @Override
    public void init(ServletConfig config) throws ServletException {
        // Servlet 객체는 싱글톤으로 관리되므로 /calculate 요청이 여러 번 들어와도 init()은 한 번만 호출된다. Tomcat loadServlet() 참고
        log.info("init");
        this.servletConfig = servletConfig;
    }
     */

    /*
    @Override
    public void destroy() {
        // resource release
    }

    @Override
    public ServletConfig getServletConfig() {
        return this.servletConfig;
    }

    @Override
    public String getServletInfo() {
        return null;
    }
     */
}
