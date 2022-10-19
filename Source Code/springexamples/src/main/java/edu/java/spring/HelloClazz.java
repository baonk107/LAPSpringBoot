package edu.java.spring;

import org.springframework.beans.factory.DisposableBean;

import java.util.List;

public class HelloClazz implements DisposableBean {
    public String message;
    private List<JavaClazz> clazzes;

    public List<JavaClazz> getClazzes() {
        return clazzes;
    }

    public void setClazzes(List<JavaClazz> clazzes) {
        this.clazzes = clazzes;
    }

    public HelloClazz() {
        message = "From Constructor: Say hello everyone by Class Hello Clazz!";
    }

    public String getMessage() {
        return message;
    }

    public HelloClazz(int person) {
        message = "From Constructor: Say hello to " + person + " person(s)!";
    }

    public HelloClazz(HelloClazz clazz) {
        message = clazz.message;
    }

    public void setMessage(String message) {
        this.message = "Call From Setter Class Hello Clazz => " + message;
    }

    public void printMessage() {
        System.out.println("Your Message: " + message);
    }

    private void initMessage() {
        System.out.println("Calling init method...");
        message = "From init method: Say hello bean!";
    }

    @Override
    public void destroy() throws Exception {
        message = null;
        System.out.println("Message is null");
    }
}
