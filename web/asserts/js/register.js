function onRegister() {

    //获取页面封装的信息
    var username = $('#username').val();
    var password = $('#password').val();
    var nickname = $('#nickname').val();


    //将信息封装为 user中
    var user = {
        username: username,
        password: password,
        nickname: nickname
    };


    //通过ajax 将user以json格式传到对应的URL为register的servlet中。若返回信息成功，中转到对应URL为Chat的页面，否则给出失败信息
    $.ajax(
        {
            url:'register',
            type:'POST',
            data:JSON.stringify(user),
            dataType:'JSON',
            contentType:'application/json;charset=utf-8',
            success:function(msg){
                if (msg.status != null && msg.status == "success") {
                    window.location.href = 'Chat';
                }else {
                    alert('注册失败 ' + "原因是： \n" + msg.reason);

                }
            },
            error:function (msg) {
                console.log('ajax发生错误' + msg);
            }
        }
    )

    //由于这个js函数是通过form表单中的onsubmit 来触发的 所以最终必须返回true或者false
    return false;

}