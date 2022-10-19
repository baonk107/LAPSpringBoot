package edu.java.web.client;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@WebServlet(value = "/j_security_check", name = "login-servlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        req.getRequestDispatcher("./login.html").forward(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out = resp.getWriter();
        String userName = req.getParameter("j_username");
        String password = req.getParameter("j_password");
        try {
            req.login(userName, password);
            req.setAttribute("say", "Hi Ha Noi!");
            req.getRequestDispatcher("hello.jsp").forward(req, resp);
        } catch (Exception e) {
            out.println("<h1>Sorry! login Failed!</h1>");
            e.printStackTrace(out);
        }
    }

    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        super.service(req, res);
    }
}
