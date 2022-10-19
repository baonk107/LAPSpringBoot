<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
    <%@ taglib uri="http://tiles.apache.org/tags-tiles" prefix="tiles" %>
        <!DOCTYPE html>
        <html lang="en">

        <head>
            <meta charset="UTF-8">
            <meta http-equiv="X-UA-Compatible" content="IE=edge">
            <meta name="viewport" content="width=device-width, initial-scale=1.0">
            <title>Document</title>
            <!-- JS -->
            <script>
                function view(id) {
                    var xmlHttp = new XMLHttpRequest();

                    xmlHttp.open("GET", "json/" + id, true);
                    xmlHttp.onload = function () {
                        if (this.status != 200)
                            return;
                        console.log(this.responseText);
                        var student = parseXmlToJson(this.responseText);
                        console.log(student);
                        
                        document.getElementById('content').innerHTML = 'Name: ' + student.Student.name
                            + " id " + student.Student.id
                            + '<img style="width: 200px; height: 200px;" src="/student/avatar/' + student.Student.id + '"/>';
                        var dialog = document.getElementById('viewStudent');
                        dialog.show();

                        // console.log(this.responseText);
                    };
                    xmlHttp.send();
                }

                function parseXmlToJson(xml) {
                    const json = {};
                    for (const res of xml.matchAll(/(?:<(\w*)(?:\s[^>]*)*>)((?:(?!<\1).)*)(?:<\/\1>)|<(\w*)(?:\s*)*\/>/gm)) {
                        const key = res[1] || res[3];
                        const value = res[2] && parseXmlToJson(res[2]);
                        json[key] = ((value && Object.keys(value).length) ? value : res[2]) || null;

                    }
                    return json;
                }
            </script>
        </head>

        <body>
            <tiles:insertDefinition name="studentTemplate">
                <tiles:putAttribute name="body">
                    <h2>List of Students</h2>
                    <form method="GET" action="list">
                        <table border="1">
                            <tr>
                                <td colspan="4"><input type="text" name="q" size="30"></td>
                                <td><input type="submit" value="Submit"></td>
                            </tr>
                            <tr>
                                <td>Id</td>
                                <td>Name</td>
                                <td>Age</td>
                                <td></td>
                                <td></td>
                            </tr>
                            <c:forEach items="${students}" var="student">
                                <tr>
                                    <td>${student.id}</td>
                                    <td>${student.name}</td>
                                    <td>${student.age}</td>
                                    <td><a href="delete/${student.id}">Delete</a></td>
                                    <td><a href="edit/${student.id}">Edit</a></td>
                                    <td><a href="javascript:view(${student.id})">${student.name}</a></td>
                                </tr>
                            </c:forEach>
                        </table>
                    </form>
                    <p><a href="/student/add">Add New</a></p>

                    <dialog id="viewStudent" style="width: 50%; border: 1px dotted black;">
                        <div id="content"></div>
                        <button id="hide">Close</button>
                    </dialog>

                    <script>
                        (function () {
                            var dialog = document.getElementById('viewStudent');
                            document.getElementById('hide').onclick = function () {
                                dialog.close();
                            };
                        })();
                    </script>
                </tiles:putAttribute>
            </tiles:insertDefinition>
        </body>

        </html>