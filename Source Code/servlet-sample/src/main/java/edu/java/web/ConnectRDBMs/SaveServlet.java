package edu.java.web.ConnectRDBMs;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.*;

@WebServlet(value = "/save", name = "save-servlet")
public class SaveServlet extends HttpServlet {
    Connection connection = null;
    Statement statement = null;

    public void init(ServletConfig config) {
        try {
            Class.forName("org.apache.derby.jdbc.EmbeddedDriver");
            connection = DriverManager.getConnection("jdbc:derby:C://Temp//userdb;create=true");
            statement = connection.createStatement();

            DatabaseMetaData dmd = connection.getMetaData();
            ResultSet rs = dmd.getTables(null, null, null, new String[]{"TABLE"});
            if (rs.next() && "hanoi_user".equals(rs.getString("TABLE_NAME"))) {
                return;
            }else {
                String sql = "create table hanoi_user(" +
                        "username varchar(500), " +
                        "password varchar(500), " +
                        "email varchar(1000))";
               // statement.execute(sql);
            }
        } catch (ClassNotFoundException | SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getParameter("action").equals("del")){
            String sql = "delete from hanoi_user where username = \'"+req.getParameter("user") +"\'";
            try {
                statement.execute(sql);
                req.getRequestDispatcher("view-users.jsp").forward(req, resp);
            } catch (SQLException e) {
                throw new RuntimeException(e);
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("username");
        String pass = req.getParameter("password");
        String email = req.getParameter("email");

//        System.out.println(user);
//        System.out.println(pass);
//        System.out.println(email);
        String sql = "insert into hanoi_user" +
                "(username, password, email) values" +
                " ('" + user + "','" + pass + "','" + email + "')";

        try {
            statement.execute(sql);
            req.getRequestDispatcher("view-users.jsp").forward(req, resp);
        } catch (SQLException e) {
            e.printStackTrace(resp.getWriter());
        }
    }

    @Override
    public void destroy() {
        try {
            connection = DriverManager.getConnection("jdbc:derby:C://Temp//userdb;shutdown=true");
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
