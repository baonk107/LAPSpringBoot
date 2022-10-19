package edu.java.spring.jdbc;

import org.apache.log4j.Logger;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import java.util.ArrayList;
import java.util.List;


public class JdbcApp {
    private static Logger LOGGER = Logger.getLogger(JdbcApp.class);

    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("beans.xml");
        StudentJdbcDAO jdbc = (StudentJdbcDAO) context.getBean("studentJdbcDAO");
        LOGGER.info("Create bean " + jdbc);
        //Demo Insert
//        jdbc.insert("Nguyen Van Tuyen", 21);

//        LOGGER.info("Total Students is " + jdbc.totalRecord());
//        System.out.println("Before modifier");
//        jdbc.loadStudent("a").forEach(System.out::println);

        //Update By Name
//        jdbc.updateAgeByName("Nguyen Van Tuyen", 25);

        //Update through XMl
//        System.out.println("After modifier");
//        jdbc.updateAgeByNameXML("Nguyen Van Tuyen", 22);
//        jdbc.loadStudent("a").forEach(student -> System.out.println(student));

        //Delete through XML
//        System.out.println("After modifier Dele");
//        jdbc.deleteByNameXML("Nguyen Van Tuyen");
//        jdbc.loadStudent("Nguyen").forEach(student -> System.out.println(student));

        //Demo BatchInsert
//        System.out.println();
//        System.out.println("After modifier");
//        List<Student> students = new ArrayList<>();
//        students.add(new Student("Nguyen Van Tuyen", 17));
//        students.add(new Student("Nguyen Ngoc Khai", 18));
//        students.add(new Student("Pham Van Dat", 19));

//        int[] values = jdbc.batchInsert(students);
//        for (int i = 0; i < values.length; i++) {
//            LOGGER.info("Add Record " + i + " : " + (values[i] == 0 ? "failed":"success"));
//        }
//        LOGGER.info("Total Students is " + jdbc.totalRecord());
//        System.out.println();
//        jdbc.loadStudent("a").forEach(System.out::println);

//        jdbc.save("Tran Thi A", 23);
    }
}
