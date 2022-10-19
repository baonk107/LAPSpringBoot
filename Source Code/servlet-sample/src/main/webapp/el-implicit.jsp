<%-- 
    Document   : el-implicit
    Created on : Sep 23, 2022, 11:24:10 AM
    Author     : KhacBao
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Demo Foreach and Jstl/Functions!</h1>
        <c:forEach var="reqHeader" items="${header}">
            ${reqHeader.key} = ${reqHeader.value}<br>
            Request Value - Leng = ${fn:length(reqHeader.value)} <br>
        </c:forEach>
    </body>
</html>
