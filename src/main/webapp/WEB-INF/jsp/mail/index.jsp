<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client-Side JavaScript Code Sample</title>
    <script type="text/javascript" src="/tShow/resources/js/mail/wl.js"></script>
    <script type="text/javascript" src="/tShow/resources/js/mail/operate.js"></script>
</head>
<body>
<div id="info"></div>

<div id="contact">
    <div>
        <button onclick="getContact()">get contact</button>
        <div id="contacts"></div>
    </div>
    <div>
        <button onclick="setContact({'first_name': '大锤', 'last_name': '王'})">set contact</button>
    </div>
</div>

<div id="calendar">
    <div>
        <button onclick="getCalendar()">get calendar</button>
        <div id="calendars"></div>
    </div>
</div>

<div id="email">
    <button onclick="getMail()">get mail</button>
    <div id="mails"></div>
</div>

<button onclick="auth()">auth</button>
</body>
</html>