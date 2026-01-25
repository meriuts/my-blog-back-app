package ru.blog;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ru.blog.config.AppConfig;

public class Application {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(AppConfig.class);
    }
}