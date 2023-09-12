package org.example.mvcPractice;

import org.example.mvcPractice.annotation.Component;
import org.example.mvcPractice.annotation.Controller;
import org.example.mvcPractice.annotation.Service;
import org.junit.jupiter.api.Test;
import org.reflections.Reflections;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.annotation.Annotation;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
}
