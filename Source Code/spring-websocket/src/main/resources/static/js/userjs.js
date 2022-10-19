var url_string = window.location.href;
        var url = new URL(url_string);
        var name_param = url.searchParams.get("name");
        console.log("Param name: " + name_param);

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
                loadGroups();
                if (name_param != null) {
                    loadInfoUser(name_param);
                }
            });

        }

        function save() {
            //Step 3
            var username = document.getElementById('username').value;
            var password = document.getElementById('password').value;
            var email = document.getElementById('email').value;
            var age = document.getElementById('age').value;
            var groupId = document.getElementById('groupId').value;

            console.log("Sending... " + username);
            if (name_param == null || name_param == "") {
                console.log("Insert");
                //Step 4
                stompClient.send("/app/user", {}, JSON.stringify({
                    'username': username,
                    'password': password,
                    'email': email,
                    'age': age,
                    'groupId': groupId,
                }));

            } else {
                console.log("Update");
                //Step 4
                stompClient.send("/app/user/" + name_param, {}, JSON.stringify({
                    'username': username,
                    'password': password,
                    'email': email,
                    'age': age,
                    'groupId': groupId,
                }));
            }

        }

        //Step 6
        function showMessage(text) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.appendChild(document.createTextNode(text));
            response.appendChild(p);
        }

        //Step 7
        function loadGroups() {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                    // console.log(xmlhttp.responseText); //Return list group

                    //Load Option Group vào Select
                    var groups = JSON.parse(xmlhttp.responseText);//Get List Groups after open and send
                    var select = document.getElementById('groupId');

                    for (var index in groups) {
                        var group = groups[index];
                        select.appendChild(createOptionGroup(group));
                    }
                }
            };
            xmlhttp.open("GET", "/groups", true);
            xmlhttp.send();
        }

        //Tạo Option for Select auto with display name and value = id
        function createOptionGroup(group) {
            var option = document.createElement('option');
            option.appendChild(document.createTextNode(group.name));
            var att = document.createAttribute("value");
            att.value = group.id;
            option.setAttributeNode(att);

            return option;
        }

        //Load Info
        function loadInfoUser(name_param) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                    console.log(xmlhttp.responseText); //Return list group

                    var users = JSON.parse(xmlhttp.responseText);
                    document.getElementById('username').value = users.username;
                    document.getElementById('password').value = users.password;
                    document.getElementById('email').value = users.email;
                    document.getElementById('age').value = users.age;
                    document.getElementById('groupId').value = users.groupId;
                    document.getElementById('username').readOnly = true;
                }
            };
            xmlhttp.open("GET", "/user/" + name_param, true);
            xmlhttp.send();
        }