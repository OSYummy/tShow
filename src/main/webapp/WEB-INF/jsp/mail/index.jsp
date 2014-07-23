<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client-Side JavaScript Code Sample</title>
    <script src="http://outlook.ngrok.com/tShow/resources/js/mail/wl.js"></script>
</head>
<body>
<div id="signin"></div>
<label id="info"></label>
<script>
    var APP_CLIENT_ID = "00000000481235D0";
    var REDIRECT_URL = "http://outlook.ngrok.com/tShow/mail/redirect.do";

    WL.Event.subscribe("auth.login", onLogin);
    WL.init({
        client_id: APP_CLIENT_ID,
        redirect_uri: REDIRECT_URL,
        scope: "wl.signin",
        response_type: "token",
        status: true,
        logging: true
    });

    WL.ui({
        name: "signin",
        element: "signin"
    });

    function onLogin(session){
        if(!session.error){
            WL.api({
                path: "me",
                method: "GET"
            }).then(function(response){
                        console.log(response);
                        document.getElementById("info").innerText
                                = "Hello," + response.first_name + " " + response.last_name + "!";
                    },
                    function(responseFailed){
                        document.getElementById("info").innerText
                                = "Error calling API: " + responseFailed.error.message;
                    }
            );
        } else {
            document.getElementById("info").innerText
                    = "Error signing in: " + session.error_description;
        }
    }
</script>
</body>
</html>