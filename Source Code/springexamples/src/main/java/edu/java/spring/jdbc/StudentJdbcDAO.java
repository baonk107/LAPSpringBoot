package edu.java.spring.jdbc;


import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.StatementCallback;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionDefinition;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class StudentJdbcDAO {
    private final static Logger LOGGER = Logger.getLogger(StudentJdbcDAO.class);
    private DataSource dataSource;
    private JdbcTemplate jdbcTemplate;
    private String insertQuery;
    private String updateAgeByNameSQL = "update STUDENT set age = ? where name = ?";
    private String updateQuery;
    private String deleteQuery;
    @Autowired
    private PlatformTransactionManager transactionManager;

    public void setDeleteQuery(String deleteQuery) {
        this.deleteQuery = deleteQuery;
    }

    public void setUpdateQuery(String updateQuery) {
        this.updateQuery = updateQuery;
    }

    public void setInsertQuery(String insertQuery) {
        this.insertQuery = insertQuery;
    }

    public void setDataSource(DataSource dataSource) {
        this.dataSource = dataSource;
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

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

    //Insert
    public void insert(String name, int age) {
        jdbcTemplate.update(insertQuery, name, age);
        LOGGER.info("Create Record Name = " + name + " Age = " + age);
    }

    //Total Record
    public int totalRecord() {
        return jdbcTemplate.execute((StatementCallback<Integer>) stmt -> {
            ResultSet rs = stmt.executeQuery("select count(*) from student");
            return rs.next() ? rs.getInt(1) : 0;
        });
    }

    //RowMapper
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

    //Load Student
    public List<Student> loadStudent(String name) {
        return jdbcTemplate.query("SELECT * FROM STUDENT WHERE NAME LIKE '%" + name + "%'",
                new StudentRowMapper());
    }

    //Update By Name
    public void updateAgeByName(String name, int age) {
        jdbcTemplate.update(updateAgeByNameSQL, age, name);
        LOGGER.info("Update Record Name = " + name + " Age = " + age);
    }

    //UpdateAgeByName through Beans.xml
    public void updateAgeByNameXML(String name, int age) {
        jdbcTemplate.update(updateQuery, age, name);
        LOGGER.info("Update Record Name = " + name + " Age = " + age);
    }

    //Delete ByName through Beans.XML
    public void deleteByNameXML(String name){
        jdbcTemplate.update(deleteQuery, name);
        LOGGER.info("Delete Record Name = " + name);
    }

    //Demo Batch Processing
    public int[] batchInsert(List<Student> students){
        List<Object[]> batch = new ArrayList<>();
        students.forEach(student -> batch.add(new Object[]{student.getName(), student.getAge()}));
        return jdbcTemplate.batchUpdate(insertQuery, batch);
    }

    //Save Transaction
    public void save(Object name, Object age){
        TransactionDefinition def = new DefaultTransactionDefinition();
        TransactionStatus status = transactionManager.getTransaction(def);

        String countQuery = "SELECT COUNT(*) FROM STUDENT";
        try {
            Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("Before save data, total record is " + total);

            String sql = "insert into Student (name, age) values (?, ?)";
            jdbcTemplate.update(sql, name, age);

            total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("After save data, total record is " + total);

            String countQuery2 = "Select COUNT(* FROM STUDENT";
            total = jdbcTemplate.queryForObject(countQuery2, Integer.class);
//            LOGGER.info("After save data, total record is" + total);
            System.out.println("Error");
            //Comit
            transactionManager.commit(status);
        }catch (Exception e){
            //error(exp, exp)
            LOGGER.error(e.getMessage());
            transactionManager.rollback(status);

            Integer total = jdbcTemplate.queryForObject(countQuery, Integer.class);
            LOGGER.info("After rollback, total record is " + total);

        }
    }
}
