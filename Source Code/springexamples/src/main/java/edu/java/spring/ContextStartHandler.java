package edu.java.spring;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;
import org.springframework.context.event.ContextStoppedEvent;

public class ContextStartHandler implements ApplicationListener<ContextStoppedEvent> {
//    @Override
//    public void onApplicationEvent(ContextStartedEvent event) {
//        System.out.println("Context started at " + event.getTimestamp());
//    }

    @Override
    public void onApplicationEvent(ContextStoppedEvent event) {
        System.out.println("Context Stopper at " + event.getTimestamp());
    }
}
