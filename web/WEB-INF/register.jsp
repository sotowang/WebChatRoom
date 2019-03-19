<%--
  Created by IntelliJ IDEA.
  User: xjtuse
  Date: 19-3-19
  Time: 下午3:25
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" onsubmit="return onRegister()" class="fm-register">
    <div class="fm-group">
        <input type="text" placeholder="用户名" name="username" id="username" required="required" autocomplete="off">
    </div>
    <div class="fm-group">
        <input type="text" placeholder="昵称" name="nickname" id="nickname" required="required" autocomplete="off">
    </div>
    <div class="fm-group">
        <input type="password" placeholder="密码" name="password" id="password" required="required" autocomplete="off">
    </div>

    <div class="operate-grid">
        <input type="submit" id="register" value="立即注册" class="btn btn-primary">
        <a class="notify">* 请填写每一项</a>
    </div>

</form>

</body>
</html>
