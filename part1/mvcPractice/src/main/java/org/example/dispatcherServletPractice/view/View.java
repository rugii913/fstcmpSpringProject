package org.example.dispatcherServletPractice.view;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public interface View { // jsp 뷰로 연결 수도 있고, http redirect 응답을 보낼 수도 있다.

    void render(Map<String, ?> model, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
