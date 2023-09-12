package org.example.mvcPractice.controller;

import org.example.mvcPractice.annotation.Controller;
import org.example.mvcPractice.annotation.RequestMapping;
import org.example.mvcPractice.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HomeController {

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String home(HttpServletRequest request, HttpServletResponse response) {
        return "home";
    }
}
