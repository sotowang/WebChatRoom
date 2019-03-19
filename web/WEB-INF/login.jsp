<%--
  Created by IntelliJ IDEA.
  User: xjtuse
  Date: 19-3-19
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>用户登陆</title>
</head>
<body>
    <div class="container">
        <div class="login-area">
            <span class="login-title">账号登陆</span>
            <form method="post" onsubmit="return onLogin()" class="fm-login">
                <div class="fm-group">
                    <span class="input-info">用户名</span>
                    <input type="text" placeholder="用户名" id="username" name="username" required="required">
                </div>
                <div class="fm-group">
                    <span class="input-info">密码</span>
                    <input type="password" placeholder="密码" id="password" name="password" required="required">
                </div>
                <input type="submit" value="登陆" class="btn-primary">
                    <div class="bottom-content">
                        <div class="link-register"><a id="register" href="javascript:;">注册账号</a> </div>
                        <div class="link-forget"><a href="javascript:;">忘记密码</a> </div>
                    </div>

            </form>
        </div>


    </div>

</body>
</html>
