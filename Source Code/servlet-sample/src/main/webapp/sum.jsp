<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>JSP Example Demo Sum</title>
</head>
<body>
    <h1>Displaying sum</h1>
    <%
        int sum = 0;
        for(int i = 0; i < 25; i++){
            sum += i;
        }
        out.println("Sum of number is " + sum);
    %>
    <h2>Sum of number is <%out.println(sum);%></h2>
    <h3>Sum of number is <%=sum%></h3>
</body>
</html>