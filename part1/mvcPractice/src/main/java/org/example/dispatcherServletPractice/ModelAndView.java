package org.example.dispatcherServletPractice;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class ModelAndView {

    private Object view; // 실제 Spring 코드와 비교하기 위해 Object 타입으로 받음
    private Map<String, Object> model = new HashMap<>();

    public ModelAndView(String viewName) {
        view = viewName;
    }

    public Map<String, ?> getModel() {
        return Collections.unmodifiableMap(model);
    }

    public String getViewName() {
        // 실제 Spring 코드와 비교하기 위해 Object 타입으로 받음
        return (this.view instanceof String ? (String) this.view : null);
    }
}
