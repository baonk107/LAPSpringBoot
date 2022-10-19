<%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page language="java" pageEncoding="UTF-8"%>
<tiles:insertDefinition name="template">
    <tiles:putAttribute name="body">
        <h1>Trang Đăng Nhập</h1>
        <c:if test="${not empty error}">
            <p>Sai tên đăng nhập hoặc mật khẩu</p>
        </c:if>


        <form name="facebookSocialLoginForm" action="/auth/facebook" method="POST">
            <input type="hidden" name="scope" value="public_profile,email,user_birthday"/>
            <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />

            <p>
                <input type="text" name="username" id="username" placeholder="Username">
             </p>
             <p>
                 <input type="password" name="password" id="password" placeholder="Password">
             </p>
             
            <button type="submit">
                <i>Sign in with Facebook</i>
            </button>

            <div class="clear"></div>
        </form>
    </tiles:putAttribute>
</tiles:insertDefinition>