$(function(){

    var appClientId = $("#appClientId").attr("value");
    var redirectUri = $("#redirectUri").attr("value");
    var callback = "http://isdk.dev.live.com/dev/isdk/callback.aspx";

    WL.init({
        client_id: appClientId,
        redirect_uri: redirectUri
    });

    $('#login').click(function(){
        WL.login({
            scope: "wl.signin",
            response_type: "token"
        }).then(
            function(response){
                WL.api({
                    path: "me",
                    method: "GET"
                }).then(
                    function(response){
                        $.ajax({
                            url: 'http://outlook.ngrok.com/tShow/mail/validate.do',
                            method: "POST",
                            data: {
                                userId : response.id,
                                firstName: response.first_name,
                                last_name: response.last_name,
                                name: response.name,
                                gender: response.gender,
                                locale: response.locale,
                                updatedTime: response.updated_time
                            },
                            success:function(response){
                                window.location.href = "http://outlook.ngrok.com/tShow/mail/operate.do"
                            }
                        });
                    },
                    function(response){

                    }
                );
            },
            function(response){

            }
        )
    });
})