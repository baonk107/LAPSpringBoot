<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <title>Spring Jazz Application</title>
</head>
<body>
    <h1>Welcome to Spring Jazz </h1>
    <p><a href="/">Trang Chủ</a>
        <a href="/group/list">List All Group</a>
        <a href="/account/listAll">List All User</a>
        <sec:authorize access="!hasAnyRole('ROLE_USER')">
            <a href="/dang-nhap" style="margin-left: 30px">Dang Nhap</a>
        </sec:authorize>
        <sec:authorize access="hasAnyRole('ROLE_USER')">
            <a href="/nguoi-dung" style="margin-left: 30px">Ca Nhan</a>
            <a href="javascript:document.getElementById('logout').submit();" style="margin-left: 30px">Dang Xuat</a>
        </sec:authorize>
    </p>
    <form action="/j_spring_security_logout" id="logout" method="post">
        <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
    </form>
    <tiles:insertAttribute name="body" />
</body>
</html>