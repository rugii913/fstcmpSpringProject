package org.example.dispatcherServletPractice;

import org.example.dispatcherServletPractice.controller.Controller;
import org.example.dispatcherServletPractice.controller.RequestMethod;
import org.example.dispatcherServletPractice.view.JspViewResolver;
import org.example.dispatcherServletPractice.view.View;
import org.example.dispatcherServletPractice.view.ViewResolver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Collections;
import java.util.List;

@WebServlet("/") // 어떤 경로를 입력하더라도 이 DispatcherServlet이 호출
// 왜 그런지는 spec을 참고해야될 것 같다... // 버전은 다르겠지만 최신 버전인 https://jakarta.ee/specifications/servlet/5.0/jakarta-servlet-spec-5.0을 참고해도 크게 상관 없을 듯
// 12.1. Use of URL Paths 12.2. Specification of Mappings + 3.5. Request Path Elements 정도 참고해보면 될 것 같다.
public class DispatcherServlet extends HttpServlet {

    private static final Logger log = LoggerFactory.getLogger(DispatcherServlet.class);
    private List<HandlerMapping> handlerMappings;
    private List<HandlerAdapter> handlerAdapters;
    private List<ViewResolver> viewResolvers;

    @Override
    public void init() throws ServletException { // DispatcherServlet이 인스턴스화될 때 호출
        // cf. 톰캣이 뜰 때 Servlet 타입 클래스를 싱글톤으로 인스턴스화하면서 그 때 init() 메서드 호출함
        // ***** 톰캣 코드 더 찾아보자 *****
        RequestMappingHandlerMapping rmhm = new RequestMappingHandlerMapping();
        rmhm.init(); // 포함하고 있는 RequestMappingHandlerMapping 객체를 초기화(맵 자료구조 초기화)

        AnnotationHandlerMapping ahm = new AnnotationHandlerMapping("org.example.dispatcherServletPractice");
        ahm.init();

        handlerMappings = List.of(rmhm, ahm);
        viewResolvers = Collections.singletonList(new JspViewResolver());
        handlerAdapters = List.of(new SimpleControllerHandlerAdapter(), new AnnotationHandlerAdapter());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        log.info("[DispatcherServlet] service started");

        String requestURI = request.getRequestURI();
        RequestMethod requestMethod = RequestMethod.valueOf(request.getMethod());

        log.info("request uri= {}", requestURI);

        try {
            Object handler = handlerMappings.stream() // uri에 맞는 컨트롤러(핸들러) 돌려받음
                    .filter(handlerMapping -> handlerMapping.findHandler(new HandlerKey(requestMethod, requestURI)) != null)
                    .map(handlerMapping -> handlerMapping.findHandler(new HandlerKey(requestMethod, requestURI)))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No handler for [" + requestMethod + ", " + requestURI + "]"));

            // 현재 redirect:~도 해결하고, forward도 해결해야하는 상황 (UserCreateController에서 return "redirect:/users"; 부분)
            // 현재 없는 것 - Handler Adapter, View Resolver -> 이 중 이 상황에서 필요한 것은 View Resolver
            // String viewName = handler.handleRequest(request, response); // 찾은 컨트롤러에 handleRequest 위임(뷰의 이름을 스트링으로 반환 받기)
            // -> 아래에서 ModelAndView를 사용하므로 이 부분은 제거
            HandlerAdapter foundHandlerAdapter = handlerAdapters.stream()
                    .filter(handlerAdapter -> handlerAdapter.supports(handler))
                    .findFirst()
                    .orElseThrow(() -> new ServletException("No adapter for handler [" + handler + "]"));

            ModelAndView modelAndView = foundHandlerAdapter.handle(request, response, handler);

            for (ViewResolver viewResolver : viewResolvers) {
                View view = viewResolver.resolveView(modelAndView.getViewName());
                view.render(modelAndView.getModel(), request, response);
            }
        } catch (Exception e) {
            log.error("exception occurred: [{}]", e.getMessage(), e);
            throw new RuntimeException(e);
        }
    }
}
