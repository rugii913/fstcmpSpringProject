package org.example.dispatcherServletPractice.repository;

import org.example.dispatcherServletPractice.model.User;

import java.util.HashMap;
import java.util.Map;

public class UserRepository {

    private static Map<String, User> users = new HashMap<>();

    public static void save(User user) {
        users.put(user.getUserId(), user);
    }
}
