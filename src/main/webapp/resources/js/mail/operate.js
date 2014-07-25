var APP_CLIENT_ID = "00000000481235D0";
var REDIRECT_URL = "http://outlook.ngrok.com/tShow/mail/redirect.do";

WL.init({
    client_id: APP_CLIENT_ID,
    redirect_uri: REDIRECT_URL
});

// 新增联系人
function createContact(contact){
    WL.login({
        scope: "wl.contacts_create"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: "me/contacts",
                method: "POST",
                body: {
                    first_name: contact.first_name,
                    last_name: contact.last_name
                }
            }).then(
                function (response) {
                    console.log(response);
                    document.getElementById("info").innerHTML = 'create success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText =
                        "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText =
                "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 读取联系人
function readContact(){
    WL.login({
        "scope": "wl.basic"
    }).then(
        function(response){
            console.log(WL.getSession());
            WL.api({
                path: "me/contacts",
                method: "GET"
            }).then(
                function (response) {
                    console.log(response);
                    var text="";
                    for(var i=0; i<response.data.length; i++){
                        var contact = response.data[i];
                        text += "ID: " + contact.id + " Name: " + contact.last_name + " " + contact.first_name + "<br />";
                    }
                    document.getElementById("contacts").innerHTML = text;
                    document.getElementById("info").innerHTML = 'read success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerHTML
                        = "API call failed: " + JSON.stringify(responseFailed.error).replace(/,/g, "\n");
                }
            );
        }, function(responseFailed){
            document.getElementById("info").innerHTML
                = "Could not connect, status = " + responseFailed.status;
        }
    );
};

// 更新联系人
function updateContact(contact){
    /*WL.login({
        scope: "wl.contacts_create"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: contact.contact_id,
                method: "PUT",
                body: {
                    first_name: contact.first_name,
                    last_name: contact.last_name
                }
            }).then(
                function (response) {
                    document.getElementById("info").innerHTML = 'update success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText =
                        "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText =
                "Error signing in: " + responseFailed.error_description;
        }
    );*/
}

// 删除联系人
function deleteContact(contact_id){
    /*WL.login({
        scope: "wl.contacts_create"
    }).then(
        function(response){
            console.log(WL.getSession());
            WL.api({
                path: contact_id,
                method: "DELETE"
            }).then(
                function(response){
                    console.log(response);
                    document.getElementById("info").innerHTML = 'delete success';
                },
                function(responseFailed){
                    document.getElementById("info").innerText =
                        "Error signing in: " + responseFailed.error_description;
                }
            );
        },
        function(responseFailed){
            document.getElementById("info").innerText =
                "Error signing in: " + responseFailed.error_description;
        }
    );*/
}

// 创建日历
function createCalendar(calendar){
    WL.login({
        scope: "wl.calendars_update"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: "me/calendars",
                method: "POST",
                body:{
                    name: calendar.name
                }
            }).then(
                function (response) {
                    console.log(response);
                    document.getElementById("info").innerHTML = 'create success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 读取日历
function readCalendar(){
    WL.login({
        scope: "wl.calendars"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: "me/calendars",
                method: "GET"
            }).then(
                function (response) {
                    var text="";
                    for(var i=0; i<response.data.length; i++){
                        var calendar = response.data[i];
                        text+="ID: " + calendar.id + " created_time: " + calendar.created_time + " Name: " + calendar.name  +"<br/>";
                    }
                    document.getElementById("calendars").innerHTML = text;
                    document.getElementById("info").innerHTML = 'read success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 更新日历
function updateCalendar(calendar){
    WL.login({
        scope: "wl.calendars_update"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: calendar.id,
                method: "PUT",
                body:{
                    name: calendar.name
                }
            }).then(
                function (response) {
                    console.log(response);
                    document.getElementById("info").innerHTML = 'update success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 删除日历
function deleteCalendar(calendar_id){
    WL.login({
        scope: "wl.calendars_update"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: calendar_id,
                method: "DELETE"
            }).then(
                function (response) {
                    console.log(response);
                    document.getElementById("info").innerHTML = 'delete success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 创建事件
function createEvent123(calendar, event){
    WL.login({
        scope: "wl.events_create"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: calendar.id + "/events",
                method: "POST",
                body:{
                    name: event.name,
                    description: event.description,
                    start_time: event.start_time,
                    end_time: event.end_time,
                    location: event.location,
                    is_all_day_event: event.is_all_day_event,
                    availability: event.availability,
                    visibility: event.visibility
                }
            }).then(
                function (response) {
                    console.log(response);
                    document.getElementById("info").innerHTML = 'create success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 读取事件
function readEvent(calendar_id){
    WL.login({
        scope: "wl.calendars"
    }).then(
        function (response) {
            console.log(WL.getSession());
            WL.api({
                path: calendar_id + "/events",
                method: "GET"
            }).then(
                function (response) {
                    var text="";
                    for(var i=0; i<response.data.length; i++){
                        var event = response.data[i];
                        text+="ID: " + event.id + " created_time: " + event.created_time + " Name: " + event.name  +"<br/>";
                    }
                    document.getElementById("events").innerHTML = text;
                    document.getElementById("info").innerHTML = 'read success';
                },
                function (responseFailed) {
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function (responseFailed) {
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 更新事件
function updateEvent(event){
    WL.login({
        scope: "wl.calendars_update"
    }).then(
        function(response){
            console.log(WL.getSession());
            WL.api({
                path: event.id,
                method: "PUT",
                body:{
                    name: event.name
                }
            }).then(
                function(response){
                    console.log(response);
                    document.getElementById("info").innerHTML = 'update success';
                },
                function(responseFailed){
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function(responseFailed){
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

// 删除事件
function deleteEvent(event_id){
    WL.login({
        scope: "wl.calendars_update"
    }).then(
        function(response){
            console.log(WL.getSession());
            WL.api({
                path: event_id,
                method: "DELETE"
            }).then(
                function(response){
                    console.log(response);
                    document.getElementById("info").innerHTML = 'delete success';
                },
                function(responseFailed){
                    document.getElementById("info").innerText
                        = "Error calling API: " + responseFailed.error.message;
                }
            );
        },
        function(responseFailed){
            document.getElementById("info").innerText
                = "Error signing in: " + responseFailed.error_description;
        }
    );
}

function auth(){
    var url = "https://login.live.com/oauth20_authorize.srf" +
        "?client_id=" + APP_CLIENT_ID +
        "&scope=" + "wl.offline_access" +
        "&response_type=code" +
        "&redirect_uri=" + "http://outlook.ngrok.com/tShow/mail/redirect.do";
    console.log(url);
    var g = window.open(url, "auth", null, null);
    g.focus();
}