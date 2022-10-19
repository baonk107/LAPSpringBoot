var stompClient = null;
        //Step 1:
        function connect() {
            var socket = new SockJS('/message');
            //Step 2
            stompClient = Stomp.over(socket);

            stompClient.connect({}, function (frame) {
                console.log('Connected: ' + frame);

                //Step 5
                stompClient.subscribe('/topic/chat', function (message) {
                    var text = JSON.parse(message.body).content;

                    showMessage(text);

                    console.log("Message Parse: " + text);
                });

                //Step 7
                loadUsers();
            });

        }

        //Step 6
        function showMessage(text) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.appendChild(document.createTextNode(text));
            response.appendChild(p);
        }

        //Step 7
        function loadUsers() {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                    console.log(xmlhttp.responseText); //Return list group

                    //Load Option Group vào Select
                    var users = JSON.parse(xmlhttp.responseText);//Get List Groups after open and send
                    var tbody = document.getElementById('tbodyUser');
                    var userStr = ``;
                    for(var index in users){
                        var user = users[index];
                        // tbody.appendChild(createRowTable(user));
                        userStr += createRowTable(user);
                    }
                    tbody.innerHTML = userStr;  
                }
            };
            xmlhttp.open("GET", "/users", true);
            xmlhttp.send();
        }

        function createRowTable(user){
            let row = `<tr>
                <td>${user.username}</td>
                <td>${user.password}</td>
                <td>${user.email}</td>
                <td>${user.age}</td>
                <td>${user.groupId}</td>
                <td><a href="/user.html?name=${user.username}">Update</a></td>
                <td><a onclick="deleteUser('${user.username}')">Delete</a></td>
            </tr>`;
            return row;
        }

        function deleteUser(username) {
            console.log("Sending... " + username);

            //Step 4
            stompClient.send("/app/user/delete/"+username, {}, JSON.stringify({

            }));
            loadUsers();
        }
