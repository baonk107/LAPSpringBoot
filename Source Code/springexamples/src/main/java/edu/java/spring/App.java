package edu.java.spring;

import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

/**
 * Hello world!
 */
public class App {
    private final static org.apache.log4j.Logger LOGGER = org.apache.log4j.Logger.getLogger(JavaClazz.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
//        HelloWorld obj1 = (HelloWorld) context.getBean("helloWorld");
//        obj1.sayHello();


//        HelloClazz obj2 = (HelloClazz) context.getBean("helloJavaClazz");
//        obj2.printMessage();
//        System.out.println(obj2 == obj1);
//
//        context.registerShutdownHook();

//        JavaClazz clazz = (JavaClazz) context.getBean("jee01");
//        Map<String, Integer> map = clazz.getStudents();
//        System.out.println("List student of Map");
//        for (Map.Entry<String, Integer> entry : map.entrySet()) {
//            System.out.println(entry.getKey() + " " + entry.getValue());
//        }
//
//        LOGGER.info("Map Implement is " + clazz.getStudents().getClass());
//        LOGGER.info("There are " + clazz.getStudents().size());

//        HelloClazz hClazz = (HelloClazz) context.getBean("helloJavaClazz");
//        LOGGER.info("Total classes is " + hClazz.getClazzes().size());

//        HelloWorld hClazz = (HelloWorld) context.getBean("helloWorld");
//        hClazz.sayHello();

        //Demo StartHandler
        context.stop();
    }
}
