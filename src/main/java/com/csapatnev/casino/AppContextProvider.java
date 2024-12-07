package com.csapatnev.casino;

import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

@Component
public class AppContextProvider
{
    private static ApplicationContext context;

    public AppContextProvider(ApplicationContext applicationContext) {
        context = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return context.getBean(beanClass);
    }
}
