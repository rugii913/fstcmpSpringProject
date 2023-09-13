package org.example.dispatcherServletPractice.controller;

import org.example.dispatcherServletPractice.model.User;
import org.example.dispatcherServletPractice.repository.UserRepository;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class UserCreateController implements Controller {

    @Override
    public String handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception { // user 추가
        UserRepository.save(new User(request.getParameter("userId"), request.getParameter("name")));
        return "redirect:/users"; // save 후 /users GET 메서드로 리다이렉트 -> UserListController로 연결될 것
    }
}
