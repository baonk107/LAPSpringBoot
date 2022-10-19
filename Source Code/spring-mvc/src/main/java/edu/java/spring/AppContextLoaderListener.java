package edu.java.spring;

import org.springframework.web.context.ContextLoaderListener;

import javax.servlet.ServletContextEvent;
import javax.servlet.annotation.WebListener;

@WebListener
public class AppContextLoaderListener extends ContextLoaderListener {
    @Override
    public void contextInitialized(ServletContextEvent event) {
        System.out.println("-------> Da Khoi Tao Ung Dung");
    }

    @Override
    public void contextDestroyed(ServletContextEvent event) {
        System.out.println("-------> Da Huy Ung Dung");
    }
}
