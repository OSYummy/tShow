<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Client-Side JavaScript Code Sample</title>
    <link rel="stylesheet" type="text/css" href="/tShow/resources/css/mail/index.css">
    <script type="text/javascript" src="/tShow/resources/js/mail/wl.js"></script>
    <script type="text/javascript" src="/tShow/resources/js/mail/operate.js"></script>
</head>
<body>
<div id="info"></div>

<%--Contact相关操作--%>
<div id="contact">
    <div>联系人操作</div>
    <div id="createContact">
        <button onclick="createContact({'first_name': '大锤', 'last_name': '王'})">增加</button>
    </div>
    <div id="readContact">
        <button onclick="readContact()">读取</button>
        <div id="contacts"></div>
    </div>
    <div id="updateContact">
        <button onclick="updateContact({})">更新</button>
    </div>
    <div id="deleteContact">
        <button onclick="deleteContact('contact.7723412a000000000000000000000000')">删除</button>
    </div>
</div>

<%--Calendar相关操作--%>
<div id="calendar">
    <div>日历操作</div>
    <div id="createCalendar">
        <button onclick="createCalendar({'name': 'My example calendar'})">增加</button>
    </div>
    <div id="readCalendar">
        <button onclick="readCalendar()">读取</button>
        <div id="calendars"></div>
    </div>
    <div id="updateCalendar">
        <button onclick="updateCalendar({
        'id': 'calendar.ced50ae3fae97990.de631707b2bb4147a31742a2ada537b5',
        'name': 'My example calendar updated'
        })">更新</button>
    </div>
    <div id="deleteCalendar">
        <button onclick="deleteCalendar('calendar.ced50ae3fae97990.7ccbcfbe9eec4dfc9eed4b29e480fb15')">删除</button>
    </div>
</div>

<%--Event相关操作--%>
<div id="event">
    <div>事件操作</div>
    <div id="createEvent">
        <button onclick="createEvent123(
        {
        'id': 'calendar.ced50ae3fae97990.7d424d5d38d54e5ca1bccbf40d21b6e3'
        },
        {
        'name': '测试事件',
        'description': 'Dinner with Cynthia\'s family',
        'start_time': '2014-07-26T01:30:00-08:00',
        'end_time': '2014-07-26T03:00:00-08:00',
        'location': '',
        'is_all_day_event': 'false',
        'availability': 'busy',
        'visibility': 'public'
        }
        )">增加</button>
    </div>
    <div id="readEvent">
        <button onclick="readEvent('calendar.ced50ae3fae97990.7d424d5d38d54e5ca1bccbf40d21b6e3')">读取</button>
        <div id="events"></div>
    </div>
    <div id="updateEvent">
        <button onclick="updateEvent({
        'id': 'event.ced50ae3fae97990.7d424d5d38d54e5ca1bccbf40d21b6e3.af2ba32659cc405d9fd94c28afab9d37',
        'name': '这也是一个测试'
        })">更新</button>
    </div>
    <div id="deleteEvent">
        <button onclick="deleteEvent('event.ced50ae3fae97990.7d424d5d38d54e5ca1bccbf40d21b6e3.af2ba32659cc405d9fd94c28afab9d37')">删除</button>
    </div>
</div>

<%--授权相关操作--%>
<div id="auth">
    <div>授权</div>
    <button onclick="auth()">授权</button>
</div>
</body>
</html>