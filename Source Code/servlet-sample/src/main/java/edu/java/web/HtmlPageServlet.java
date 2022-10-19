package edu.java.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.HttpConstraint;
import javax.servlet.annotation.ServletSecurity;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@ServletSecurity(
        @HttpConstraint(
                transportGuarantee = ServletSecurity.TransportGuarantee.CONFIDENTIAL,
                rolesAllowed = {"read"}
        )
)
@WebServlet(value = "/html", name = "html-servlet")
public class HtmlPageServlet extends HttpServlet {
    protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
        PrintWriter writer = res.getWriter();

        writer.println("<html><head><title> Welcome to our website! </title></head>");
        writer.println("<body><h1>Student</h1></body>");
        writer.println("<table border=\"1\">\n" +
                "        <tbody>\n" +
                "            <tr>\n" +
                "                <td>#</td>\n" +
                "                <td>Name</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>1</td>\n" +
                "                <td>Pham Van Dat</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>2</td>\n" +
                "                <td>Nguyen Van Tuyen</td>\n" +
                "            </tr>\n" +
                "            <tr>\n" +
                "                <td>3</td>\n" +
                "                <td>Nguyen Ngoc Khai</td>\n" +
                "            </tr>\n" +
                "        </tbody>\n" +
                "    </table>");
        writer.println("</html>");
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doGet(req, resp);
    }
}
