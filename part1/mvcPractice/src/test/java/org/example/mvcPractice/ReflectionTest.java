package org.example.mvcPractice;

import org.example.mvcPractice.annotation.Component;
import org.example.mvcPractice.annotation.Controller;
import org.example.mvcPractice.annotation.Service;
import org.example.mvcPractice.model.User;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * @Controller 어노테이션이 설정되어 있는 모든 클래스를 찾아서 출력한다.
 */
public class ReflectionTest {

    private final static Logger logger = LoggerFactory.getLogger(ReflectionTest.class);

    @Test
    void controllerScan() {
        Set<Class<?>> beans = getTypesAnnotatedWith(Component.class);
        logger.debug("beans: [{}]", beans);
    }

    private Set<Class<?>> getTypesAnnotatedWith(Class<? extends Annotation> annotationClazz) {
        Reflections reflections = new Reflections("org.example.mvcPractice");

        Set<Class<?>> beans = new HashSet<>();
        beans.addAll(reflections.getTypesAnnotatedWith(annotationClazz));

        return beans;
    }
    
    @Test // 클래스에 대한 모든 정보를 출력하는 테스트 메서드
    void showClass() {
        Class<User> clazz = User.class;
        logger.debug(clazz.getName());

        // 모든 필드 출력 - private까지 출력된다.
        logger.debug("User all declared fields: [{}]", Arrays.stream(clazz.getDeclaredFields()).collect(Collectors.toList()));
        // 모든 생성자 출력
        logger.debug("User all declared constructors: [{}]", Arrays.stream(clazz.getDeclaredConstructors()).collect(Collectors.toList()));
        // 모든 메서드 출력
        logger.debug("User all declared methods: [{}]", Arrays.stream(clazz.getDeclaredMethods()).collect(Collectors.toList()));
    }
}
