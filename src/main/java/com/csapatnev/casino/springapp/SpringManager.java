package com.csapatnev.casino.springapp;

import org.springframework.boot.SpringApplication;
import org.springframework.context.ConfigurableApplicationContext;

public class SpringManager {

    private static ConfigurableApplicationContext ctx;

    public static void startSpringApp() {
        ctx = SpringApplication.run(SpringApp.class, "");
    }

    public static void stopSpringApp() {
        if (ctx != null) {
            ctx.close();
        }
    }

    public SpringManager(ConfigurableApplicationContext applicationContext) {
        ctx = applicationContext;
    }

    public static <T> T getBean(Class<T> beanClass) {
        return ctx.getBean(beanClass);
    }
}
