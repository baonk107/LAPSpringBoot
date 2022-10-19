<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <sql:setDataSource var="userdb" driver="org.apache.derby.jdbc.EmbeddedDriver"
                           url="jdbc:derby:C:/Temp/userdb"/>
        <sql:query dataSource="${userdb}" var="result">
            select * from hanoi_user
        </sql:query>
        <table border="1">
            <thead>
                <tr>
                    <th>UserName</th>
                    <th>Password</th>
                    <th>Email</th>
                    <th>Action</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach var="row" items="${result.rows}">
                    <tr>
                        <td>${row.username}</td>
                        <td><c:out value="${row.password}"/></td>
                        <td>${row.email}</td>
                        <td><a href="save?action=del&user=${row.username}">Del</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <div><a href="register.html">Add New</a></div>
    </body>
</html>
