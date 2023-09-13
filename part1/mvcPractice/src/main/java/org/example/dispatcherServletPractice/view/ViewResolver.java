package org.example.dispatcherServletPractice.view;

public interface ViewResolver { // viewName을 받아서 view를 결정

    View resolveView(String viewName);
}
