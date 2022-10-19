<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>User List</h1>
        <table border="1">
            <tr>
                <td>Account</td>
                <td>Name</td>
                <td>Age</td>
                <td>#</td>
            </tr>
            <c:forEach items="${users}" var="item" varStatus="loop">
                <tr>
                    <td>Username: ${item.username}</td>
                    <td><a href="/account/detail/${item.username}">${item.username}</a></td>
                    <td>${item.age}</td>
                    <td><a href="/account/delete/${item.username}">Delete</a></td>
                </tr>
            </c:forEach>
        </table>
        <c:if test="${average != null}">
            <p>"Average of Age is ${average}"</p>
        </c:if>
    </tiles:putAttribute>
</tiles:insertDefinition>