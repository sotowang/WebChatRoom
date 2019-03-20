$(document).ready(function(){
    $('#register').click(function(){
        window.location.href = 'Register';
    })
})

function onLogin() {
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var user = {
        username: username,
        password: password
    }
    $.ajax({
        url: 'login',
        type: 'POST',
        data: JSON.stringify(user),
        dataType: 'JSON',
        contentType: 'application/json;charset=utf-8',
        success: function (msg) {
            if (msg.status != null && msg.status == "success") {
                window.location.href = "Chat";
            } else {
                alert(msg.reason);
            }
        },
        error: function (msg) {
            console.log(msg);
        }
    })
    return false
}