package edu.java.spring.dao.impl;

import edu.java.spring.dao.StudentDAO;
import edu.java.spring.model.Student;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

@Component
public class StudentDAOImpl implements StudentDAO, DisposableBean {
    private final static Logger LOGGER = Logger.getLogger(StudentDAOImpl.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;

    @Autowired
    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    @PostConstruct
    private void createTableIfNotExist() {
        try {
            DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "STUDENT", null);
            if (rs.next()) {
                LOGGER.info("Table " + rs.getString("TABLE_NAME") + " aleady exists!");
                return;
            }

            jdbcTemplate.execute("CREATE TABLE STUDENT ("
                    + " id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
                    + " name VARCHAR(1000),"
                    + " age INTEGER)");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public void insert(Student student) {
        System.out.println("1");
        jdbcTemplate.update("INSERT INTO STUDENT (name, age) VALUES (?,?)",
                student.getName(), student.getAge());
        System.out.println("2");
        LOGGER.info("Create Record Name = " + student.getName());
    }

    private final static class StudentRowMapper implements RowMapper<Student> {
        @Override
        public Student mapRow(ResultSet rs, int rowNum) throws SQLException {
            Student student = new Student();
            student.setId(rs.getInt("id"));
            student.setName(rs.getString("name"));
            student.setAge(rs.getInt("age"));
            return student;
        }
    }

    @Override
    public List<Student>    list() {
        System.out.println("List Student Ok");
        return jdbcTemplate.query("SELECT * FROM STUDENT", new StudentRowMapper());
    }

    //SearchListById
    @Override
    public List<Student> listStudentById(String sid) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE ID = " + sid, new StudentRowMapper());
    }

    //Delete
    @Override
    public void delete(String id) {
        jdbcTemplate.execute("DELETE FROM STUDENT WHERE ID = " + id);
    }

    //Update Student
    @Override
    public void update(Student student) {
        jdbcTemplate.update("UPDATE STUDENT SET NAME = ?, AGE = ? WHERE ID = ?", student.getName(), student.getAge(), student.getId());
    }

    //List Student by ID
    @Override
    public Student getById(int id) {
        return jdbcTemplate.queryForObject("SELECT * FROM STUDENT WHERE ID = " + id, new StudentRowMapper());
    }

    @Override
    public void destroy() throws Exception {
        DriverManager.getConnection("jdbc:derby:C:/JavaSpring/sampledb2;shutdown=true");
    }
}
