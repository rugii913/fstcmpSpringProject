package org.example.dispatcherServletPractice.view;

import static org.example.dispatcherServletPractice.view.RedirectView.DEFAULT_REDIRECT_PREFIX;

public class JspViewResolver implements ViewResolver {

    @Override
    public View resolveView(String viewName) {
        if (viewName.startsWith(DEFAULT_REDIRECT_PREFIX)) {
            return new RedirectView(viewName);
        } else {
            return new JspView(viewName + ".jsp");
        }
    }
}
