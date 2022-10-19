<%-- 
    Document   : el-loop
    Created on : Sep 23, 2022, 11:00:22 AM
    Author     : KhacBao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Demo el-loop</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <c:forEach var="i" begin="1" end="8" step="1">
            ${i}
            <c:choose>
                <c:when test="${i%2==0}"> hello ${i} <br></c:when>
                <c:otherwise>Not Found! <br></c:otherwise>
            </c:choose>
        </c:forEach>
    </body>
</html>
