<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page Demo Forward</title>
    </head>
    <body>
        <% String agent = request.getHeader("user-agent");%>
        
        <%if (agent.contains("Chrome")) {%>
        <h1>OK</h1>
        <jsp:forward page="hello.jsp"/>
        <%} else {%>
        <h1>Not Ok</h1>
        <jsp:forward page="sum.jsp" />
        <%}%>
    </body>
</html>
