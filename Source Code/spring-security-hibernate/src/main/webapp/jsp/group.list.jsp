<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Group List</h1>
        <table border="1">
            <tr>
                <td>Name</td>
                <td>#</td>
            </tr>
            <tr>
                <td colspan="2">
                    <form method="get" action="/group/list">
                        <input type="text" name="q">
                    </form>
                </td>
            </tr>
            <c:forEach items="${groups}" var="item" varStatus="loop">
                <tr>
                    <td><a href="/account/list?group=${item.id}">${item.name}</a>
                        <ul>
                            <c:forEach items="${item.users}" var="user" varStatus="loop">
                                <li>${user.username} - ${user.age}</li>
                            </c:forEach>
                        </ul>
                    </td>
                    <td><a href="delete/${item.id}">Delete</a></td>
                    <td><a href="update?id=${item.id}">Update</a></td>
                </tr>
            </c:forEach>
        </table>
    </tiles:putAttribute>
</tiles:insertDefinition>