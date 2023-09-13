package org.example.dispatcherServletPractice;

public interface HandlerMapping {

    void init();

    // 어노테이션 형태로도 받을 수 있게 하기 위해 Object를 반환
    Object findHandler(HandlerKey handlerKey);
}
