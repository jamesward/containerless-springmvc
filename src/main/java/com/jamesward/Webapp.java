package com.jamesward;

import org.eclipse.jetty.server.Server;
import org.eclipse.jetty.servlet.ServletContextHandler;
import org.eclipse.jetty.servlet.ServletHolder;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

import javax.servlet.DispatcherType;
import java.util.EnumSet;


public class Webapp {

    public static void main(String[] args) throws Exception {

        final AnnotationConfigWebApplicationContext applicationContext = new AnnotationConfigWebApplicationContext();
        applicationContext.register(WebConfig.class);

        final ServletHolder servletHolder = new ServletHolder(new DispatcherServlet(applicationContext));
        final ServletContextHandler context = new ServletContextHandler();
        context.setContextPath("/");
        context.addServlet(servletHolder, "/*");
        context.addFilter(HttpsEnforcer.class, "/*", EnumSet.of(DispatcherType.REQUEST));

        String webPort = System.getenv("PORT");
        if (webPort == null || webPort.isEmpty()) {
            webPort = "8080";
        }

        final Server server = new Server(Integer.valueOf(webPort));

        server.setHandler(context);

        server.start();
        server.join();
    }

}
