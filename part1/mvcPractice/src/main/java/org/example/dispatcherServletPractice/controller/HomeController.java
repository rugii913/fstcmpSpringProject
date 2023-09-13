package org.example.dispatcherServletPractice.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class HomeController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home"; // HomeController라는 컨트롤러가 호출되면 home이라는 화면을 띄워줄 것
    }
}
