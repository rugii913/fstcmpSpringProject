package org.example.dispatcherServletPractice;

import org.example.dispatcherServletPractice.annotation.Controller;
import org.example.dispatcherServletPractice.annotation.RequestMapping;
import org.example.dispatcherServletPractice.controller.RequestMethod;
import org.reflections.Reflections;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

public class AnnotationHandlerMapping implements HandlerMapping {

    private final Object[] basePackage;
    private Map<HandlerKey, AnnotationHandler> handlers = new HashMap<>();

    public AnnotationHandlerMapping(Object... basePackage) {
        this.basePackage = basePackage;
    }

    @Override
    public void init() {
        Reflections reflections = new Reflections(basePackage);
        Set<Class<?>> clazzesWithControllerAnnotation = reflections.getTypesAnnotatedWith(Controller.class);
        clazzesWithControllerAnnotation
                .forEach(clazz -> Arrays.stream(clazz.getDeclaredMethods()).forEach(declaredMethods -> {
                    RequestMapping requestMapping = declaredMethods.getDeclaredAnnotation(RequestMapping.class);
                    // ex. @RequestMapping(value = "/", method = {RequestMethod.GET})
                    Arrays.stream(getRequestMethods(requestMapping))
                            .forEach(requestMethod -> handlers.put(
                                    new HandlerKey(requestMethod, requestMapping.value()), new AnnotationHandler(clazz, declaredMethods)));
                }));
    }

    private RequestMethod[] getRequestMethods(RequestMapping requestMapping) {
        return requestMapping.method();
    }

    @Override
    public Object findHandler(HandlerKey handlerKey) {
        return handlers.get(handlerKey);
    }
}
