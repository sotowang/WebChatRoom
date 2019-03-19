function getOnlineUser() {
    $.ajax({
        url:'userlist',
        type:'GET',
        success: function (data) {
            var onlineUserTemplate = '\
                <li class=""user-cell">\
                    <span class="user-info">用户名：<a class="user-name">{{username}}</a> \
                    昵称:<a class="user-nick">{{nickname}}</a></span>\
                </li>';

            var htmls = []
            var allUsers = data;
            //将用户信息一个个添加到htmls数组中
            if (allUsers instanceof Array) {
                for (var i = 0; i < allUsers.length; i++) {
                    var user = allUsers[i];
                    var tempHtml = onlineUserTemplate.replace(/{{username}}/,
                        user.username).replace(/{{nicjname}}/, user.nickname);
                    htmls.push(tempHtml);
                }
            }
            $('.users-list').html('');  //清空用户列表中的内容// 
            
            //将htmls数组中的用户信息填充到用户列表中
            $('.users-list').append(htmls.join(''));
        },
        error:function () {
            
        }
        
        
    })
}