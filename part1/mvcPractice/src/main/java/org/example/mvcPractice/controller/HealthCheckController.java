package org.example.mvcPractice.controller;

import org.example.mvcPractice.annotation.Controller;
import org.example.mvcPractice.annotation.RequestMapping;
import org.example.mvcPractice.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@Controller
public class HealthCheckController {

    @RequestMapping(value = "/health", method = RequestMethod.GET)
    public String health(HttpServletRequest request, HttpServletResponse response) {
        return "ok";
    }
}
