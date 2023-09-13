package org.example.dispatcherServletPractice;

import org.example.dispatcherServletPractice.controller.*;

import java.util.HashMap;
import java.util.Map;

public class RequestMappingHandlerMapping { // String key는 url path, Controller value는 url path에 매핑해줄 컨트롤러

    private Map<HandlerKey, Controller> mappings = new HashMap<>();

    void init() {
        mappings.put(new HandlerKey(RequestMethod.GET, "/"), new HomeController()); // 어떤 경로도 설정되지 않으면 HomeController로 매핑하는 매핑 정보
        mappings.put(new HandlerKey(RequestMethod.GET, "/users"), new UserListController());
        mappings.put(new HandlerKey(RequestMethod.POST, "/users"), new UserCreateController());
        mappings.put(new HandlerKey(RequestMethod.GET, "/user/form"), new ForwardController("/user/form"));
    }

    public Controller findHandler(HandlerKey handlerKey) { // cf. Handler는 Controller라고 생각하면 된다.
        // DispatcherServlet이 RequestMappingHandlerMapping을 포함하고 있다가
        // 어떤 uri 요청이 들어오면 findHandler(uriPath)를 호출해서 uri 요청에 맞는 컨트롤러(핸들러)를 매핑
        return mappings.get(handlerKey);
    }
}
