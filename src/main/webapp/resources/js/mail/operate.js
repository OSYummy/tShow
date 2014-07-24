var APP_CLIENT_ID = "00000000481235D0";
var REDIRECT_URL = "https://login.live.com/oauth20_desktop.srf";

WL.init({client_id: APP_CLIENT_ID, redirect_uri: REDIRECT_URL});

// 获取用户的联系人信息
function getContact(){
    WL.login({
        "scope": "wl.basic"
    }).then(
        function(response){
            WL.api({
                path: "me/contacts",
                method: "GET"
            }).then(
                function (response) {
                    console.log(WL.getSession());
                    console.log(response);
                    for(var i=0; i<response.data.length; i++){
                        var contact = response.data[i];
                        document.getElementById("contacts").innerHTML +=
                            "ID: " + contact.id + " Name: " + contact.last_name + " " + contact.first_name + "<br />";
                    }
                },
                function (response) {
                    document.getElementById("contacts").innerHTML
                        = "API call failed: " + JSON.stringify(response.error).replace(/,/g, "\n");
                }
            );
        }, function(response){
            document.getElementById("contacts").innerHTML
                = "Could not connect, status = " + response.status;
        }
    );
};

// 新增联系人
function setContact(contact){
    WL.login({
        scope: "wl.contacts_create"
    }).then(
        function (response) {
            WL.api({
                path: "me/contacts",
                method: "POST",
                body: {
                    first_name: contact.first_name,
                    last_name: contact.last_name
                }
            }).then(
                function (response) {
                    document.getElementById("info").innerHTML = 'success';
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

// 更新联系人
function updataContact(contact){
    WL.login({
        scope: "wl.contacts_create"
    }).then(
        function (response) {
            WL.api({
                path: "contact." + contact.id,
                method: "PUT",
                body: {
                    first_name: contact.first_name,
                    last_name: contact.last_name
                }
            }).then(
                function (response) {
                    document.getElementById("info").innerHTML = 'success';
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

function getCalendar(){
    WL.login({
        scope: ["wl.calendars", "wl.events_create"]
    }).then(
        function (response) {
            WL.api({
                path: "/calendar.ced50ae3fae97990.7d424d5d38d54e5ca1bccbf40d21b6e3/events?limit=10&offset=0",
                method: "GET"
            }).then(
                function (response) {
                    console.log(response)
                    for(var i=0; i<response.data.length; i++){
                        var calendar = response.data[i];
                        document.getElementById("calendars").innerHTML
                            += "ID: " + calendar.id + " created_time: " + calendar.created_time + " Name: " + calendar.name  +"<br/>";
                    }
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