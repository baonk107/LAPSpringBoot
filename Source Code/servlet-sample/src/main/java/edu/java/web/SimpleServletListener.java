package edu.java.web;


import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

@WebListener()
public class SimpleServletListener implements ServletContextListener {
    @Override
    public void contextDestroyed(ServletContextEvent sce) {
        System.out.println("\n\nServletContextListener destroyed\n\n");
    }

    @Override
    public void contextInitialized(ServletContextEvent sce) {
        System.out.println("\n\nServletContextListener started\n\n");
    }
}
