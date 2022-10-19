<!DOCTYPE html>
<!--Vo hieu hoa JSP thay thành  $ {...}--> 
<%@page isELIgnored="false" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Welcome to our Website</title>
    </head>
    <body>
    <h1>${say}</h1>
    <marquee style="font-weight: 800; color: #FF0033;">
        Chào ${param["j_username"]}!!! May da login thanh cong roi day 
        <br>
        <span style="color: #0000FF;">Hostname: <%=request.getRemoteHost()%></span> <br>
        <span style="color: #0000FF;">Session ID: <%= session.getId()%></span> <br>
    </marquee>
    <a href="j_security_check">Back to Login</a>
</body>
</html>
