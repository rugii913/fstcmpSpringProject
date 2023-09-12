package org.example.mvcPractice;

import org.assertj.core.api.Assertions;
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
    
    @Test // 힙 영역에 로드되어 있는 클래스 타입의 객체를 가져오는 방법
    void load() throws ClassNotFoundException {
        // (1)
        Class<User> clazz1 = User.class;

        // (2)
        User user = new User("userId", "name");
        Class<? extends User> clazz2 = user.getClass();

        // (3)
        Class<?> clazz3 = Class.forName("org.example.mvcPractice.model.User");

        logger.debug("clazz1: [{}]", clazz1);
        logger.debug("clazz2: [{}]", clazz2);
        logger.debug("clazz3: [{}]", clazz3);

        // 이들 클래스 타입 객체들은 모두 같음(힙 영역에 로드되어 있는 객체를 가져올 뿐이므로)
        Assertions.assertThat(clazz1).isSameAs(clazz2);
        Assertions.assertThat(clazz2).isSameAs(clazz3);
        Assertions.assertThat(clazz3).isSameAs(clazz1);

        Assertions.assertThat(clazz1 == clazz2).isTrue();
        Assertions.assertThat(clazz2 == clazz3).isTrue();
        Assertions.assertThat(clazz3 == clazz1).isTrue();
    }
}
