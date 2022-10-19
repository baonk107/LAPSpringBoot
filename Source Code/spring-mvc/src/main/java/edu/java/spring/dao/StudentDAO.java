package edu.java.spring.dao;

import edu.java.spring.model.Student;

import java.util.List;

public interface StudentDAO {
    public void insert(Student student);

    public List<Student> list();

    public List<Student> listStudentById(String id);

    public void delete(String id);

    public void update(Student student);

    public Student getById(int id);
}
