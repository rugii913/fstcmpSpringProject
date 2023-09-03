package org.example.servlet;

import org.example.oopPractice.calculator.domain.Calculator;
import org.example.oopPractice.calculator.domain.PositiveNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.*;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/calculate")
public class CalculatorServlet extends HttpServlet {
    /*
      Servlet 인터페이스: 서블릿 컨테이너(ex. 톰캣)와 상호작용하기 위한 메서드들(1.생명주기 관련 메서드들, 2.기타 메타정보 메서드들)
      Servlet - GenericServlet - HttpServlet 차례대로 구현
     */
    private static final Logger log = LoggerFactory.getLogger(CalculatorServlet.class);
    // private ServletConfig servletConfig;
    // GenericServlet의 경우, service(~)만 구현하는 것도 가능하다.

    @Override
    // HttpService의 service(~) 메서드는 Get 요청은 doGet, Post 요청은 doPost, ... HTTP 메서드에 따라 HttpServlet 메서드를 호출하도록 연결
    // ServletRequest가 아니라 HttpServletRequest를 파라미터로 갖고 있으므로 이들의 기능을 활용할 수 있다.
    // HttpServlet의 doGet(~) 원래 메서드는 sendMethodNotAllowed(~)로 튕겨냄 - 재정의해줘야 동작한다.
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // log.info("service");
        int operand1 = Integer.parseInt(request.getParameter("operand1"));
        String operator = request.getParameter("operator");
        int operand2 = Integer.parseInt(request.getParameter("operand2"));

        int result = Calculator.calculate(new PositiveNumber(operand1), operator, new PositiveNumber(operand2));

        PrintWriter writer = response.getWriter();
        writer.println(result);
    }

    /*
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
     */

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
