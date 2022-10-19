<%@page isErrorPage="true" import="java.io.*" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Document</title>
</head>
<body>
    <%
        Exception exp = (Exception)request.getAttribute("javax.servlet.error.exception");
        exp.printStackTrace(new java.io.PrintWriter(out));
    %>
</body>
</html>