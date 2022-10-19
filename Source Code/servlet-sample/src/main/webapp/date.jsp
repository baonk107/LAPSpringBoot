<!DOCTYPE html>
<%@page import="java.util.Date" %>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>JSP Example Demo Date JSP</title>
    </head>
    <body>
        <ul>
            <li>Today's date is: <%= new java.util.Date()%></li>
            <li>Session Id: <%=session.getId()%></li>
        </ul>
        <h2>Demo add import</h2>
        <%response.setHeader("Refresh", "6");%>
        <ul>
            <li>Today's date is: <%= new Date()%></li>
            <li>Session Id: <%=session.getId()%></li>
        </ul>
    </body>
</html>