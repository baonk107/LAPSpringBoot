package edu.hanoi.jazz;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextStartedEvent;

import javax.sql.DataSource;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ContextStartEventHandler implements ApplicationListener<ContextStartedEvent> {
    private final static Logger LOGGER = Logger.getLogger(ContextStartEventHandler.class);

    @Autowired
    private DataSource dataSource;
    @Override
    public void onApplicationEvent(ContextStartedEvent event) {
        LOGGER.info("Context start application: " + dataSource);
        try {
//            createTable("HN_GROUP", "create table HN_GROUP("
//                    + " id BIGINT PRIMARY KEY GENERATED ALWAYS AS IDENTITY (START WITH 1, INCREMENT BY 1),"
//                    + " name varchar(100))");

            //Create Table
            createTable("HN_USER", "create table HN_USER("
            + " username VARCHAR(100) primary key,"
            + " password VARCHAR(100),"
            + " email VARCHAR(100),"
            + " age INTEGER,"
            + " groupId bigint,"
            + " CONSTRAINT GROUP_FK FOREIGN KEY (groupId) REFERENCES HN_GROUP(id))");
        }catch (Exception ex){
            LOGGER.error(ex, ex);
        }
    }

    private void createTable(String name, String script) throws SQLException{
        DatabaseMetaData dbmd = dataSource.getConnection().getMetaData();
        ResultSet rs = dbmd.getTables(null, null, name, null);
        if(rs.next()){
            LOGGER.info("Table " + rs.getString("TABLE_NAME") + " already exits!");
            return;
        }
        dataSource.getConnection().createStatement().executeUpdate(script);
    }
}
