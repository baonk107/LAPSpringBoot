<%-- 
    Document   : el-if
    Created on : Sep 23, 2022, 11:06:20 AM
    Author     : KhacBao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>escapeXml="false" Tự động hiển thị các ký tự đặc biệt 
            thay vì hiển thị các mã HTML
            <br>
            Demo: http://localhost:8080/test/el-if.jsp?person=h2(Bao)h2
        </h1>
        <c:if test="${param.person != null}">
            <c:out value="hello ${param.person}" escapeXml="false"/>
        </c:if>
    </body>
</html>
