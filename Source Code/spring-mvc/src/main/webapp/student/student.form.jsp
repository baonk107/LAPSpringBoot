<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %> 
<!DOCTYPE html>
<html lang="en">
    <head>
        <meta charset="UTF-8">
        <meta http-equiv="X-UA-Compatible" content="IE=edge">
        <meta name="viewport" content="width=device-width, initial-scale=1.0">
        <title>Document</title>
    </head>
    <body>
        <h2>Please Input Student Information</h2>
        <form:form method="POST" action="save">
            <table>
                <form:hidden path="id"/>
                <tr>
                    <td>Name: </td>
                    <td><form:input path="name"/> <form:errors path="name"/></td>
                </tr>
                <tr>
                    <td>Age: </td>
                    <td><form:input path="age" type="number"/> <form:errors path="age"/></td>
                </tr>
                <tr>
                    <td colspan="2">
                        <input type="submit" value="Submit">
                    </td>
                </tr>
            </table>
        </form:form>

        <c:if test="${id != null}">
            <h1>Please Upload a Image</h1>
            <form method="POST" action="../avatar/save" enctype="multipart/form-data">
                <input type="hidden" name="id" value="${id}"/>
                <input type="file" name="file"/>
                <input type="submit" value="Upload"/>
            </form>
        </c:if>
    </body>
</html>