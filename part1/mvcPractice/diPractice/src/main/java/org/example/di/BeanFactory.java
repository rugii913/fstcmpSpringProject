package org.example.di;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.*;

public class BeanFactory {

    private final Set<Class<?>> preInstantiatedClazz;
    private Map<Class<?>, Object> beans = new HashMap<>();

    public BeanFactory(Set<Class<?>> preInstantiatedClazz) {
        this.preInstantiatedClazz = preInstantiatedClazz;
        initialize();
    }

    private void initialize() {
        for (Class<?> clazz : preInstantiatedClazz) {
            Object instance = createInstance(clazz);
            beans.put(clazz, instance);
        }
    }

    // ex. UserController.class가 인자로 들어간다면?
    // -> UserService.class를 인자로 다시 호출 
    private Object createInstance(Class<?> clazz) {
        // 생성자 조회 - UserController의 생성자
        Constructor<?> constructor = findConstructor(clazz);
        
        // 가져온 생성자의 파라미터 정보 조회 - UserController의 생성자의 파라미터 정보
        // // UserService.class가 인자로 들어온 경우 생성자의 파라미터가 없음
        List<Object> parameters = new ArrayList<>();
        for (Class<?> typeClass : constructor.getParameterTypes()) {
            // typeClass == UserController의 생성자의 파라미터 UserService.class
            // UserService.class를 getParameterByClass에 인자로 넘겨 호출
            parameters.add(getParameterByClass(typeClass));
        }
        // // // 위를 거치면 UserService를 빈으로 등록시키고 그 객체를 파라미터로 가져옴
        
        // 인스턴스 생성
        // // UserService의 인스턴스 생성
        // // // 등록시킨 UserService 빈을 파라미터로 받아서 인스턴스 생성
        try {
            return constructor.newInstance(parameters.toArray());
        } catch (InstantiationException | IllegalAccessException | InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }

    private Constructor<?> findConstructor(Class<?> clazz) {
        Constructor<?> constructor = BeanFactoryUtils.getInjectedConstructor(clazz);

        if (Objects.nonNull(constructor)) {
            return constructor;
        }

        return clazz.getConstructors()[0];
    }

    // // ex. UserController.class가 인자로 들어간다면? 상황 이어서
    private Object getParameterByClass(Class<?> typeClass) {
        // // getBean(UserService.class)하려고 했지만 아마 이 타입 빈은 없는 상태일 것
        Object instanceBean = getBean(typeClass);

        if (Objects.nonNull(instanceBean)) {
            return instanceBean;
        }

        // // UserService.class 객체를 생성 시도
        return createInstance(typeClass);
    }

    public <T> T getBean(Class<T> requiredType) {
        return (T) beans.get(requiredType);
    }
}
