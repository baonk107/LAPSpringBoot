var url_string = window.location.href;
        var url = new URL(url_string);
        var id_param = url.searchParams.get("id");
        console.log("Param name: " + id_param);

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

                    loadInfoGroup(id_param);
            });

        }

        function save() {
            //Step 3
            var id = document.getElementById('id').value;
            var name = document.getElementById('name').value;

            console.log("Sending Id Group... " + id);
                console.log("Update");
                //Step 4
                stompClient.send("/app/group/" + id_param, {}, JSON.stringify({
                    'id': id,
                    'name': name,
                }));

        }

        //Step 6
        function showMessage(text) {
            var response = document.getElementById('response');
            var p = document.createElement('p');
            p.appendChild(document.createTextNode(text));
            response.appendChild(p);
        }

        //Step 7
        function loadInfoGroup(id) {
            var xmlhttp = new XMLHttpRequest();
            xmlhttp.onreadystatechange = function () {
                if (xmlhttp.readyState == XMLHttpRequest.DONE) {
                    console.log(xmlhttp.responseText); //Return list group

                    //Load Option Group vào Select
                    var groups = JSON.parse(xmlhttp.responseText);//Get List Groups after open and send
                    
                    document.getElementById('id').value = groups.id;
                    document.getElementById('name').value = groups.name;
                    document.getElementById('id').readOnly = true;
                }
            };
            xmlhttp.open("GET", "/group/"+id, true);
            xmlhttp.send();
        }

