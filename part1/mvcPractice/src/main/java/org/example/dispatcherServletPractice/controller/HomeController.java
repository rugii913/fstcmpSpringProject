package org.example.dispatcherServletPractice.controller;

import org.example.dispatcherServletPractice.annotation.Controller;
import org.example.dispatcherServletPractice.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = {RequestMethod.GET})
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        return "home"; // HomeController라는 컨트롤러가 호출되면 home이라는 화면을 띄워줄 것
    }
}
