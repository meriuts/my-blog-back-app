package ru.blog;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;
import ru.blog.config.WebConfiguration;

public class Application {
    public static void main(String[] args) throws Exception {
        AnnotationConfigWebApplicationContext context = new AnnotationConfigWebApplicationContext();
        context.register(WebConfiguration.class);

        DispatcherServlet dispatcherServlet = new DispatcherServlet(context);

        // Поднимаем Jetty
        Server server = new Server(8088);
        ServletContextHandler handler = new ServletContextHandler(server, "/");
        handler.addServlet(new org.eclipse.jetty.servlet.ServletHolder(dispatcherServlet), "/*");

        server.start();
        server.join();
    }
}