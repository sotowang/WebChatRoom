<%--
  Created by IntelliJ IDEA.
  User: xjtuse
  Date: 19-3-19
  Time: 下午4:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<% String path = request.getContextPath();%>

<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <link rel="stylesheet" type="text/css" href="asserts/css/login.css">

    <script src="asserts/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="asserts/js/login.js"></script>

    <title>用户登录</title>
</head>
<script type="text/javascript">
    function check(){
        if(document.getElementById("form1").username.value==""){
            alert("请输入用户名！");
            document.getElementById("form1").username.focus();
            return false;
        }

        if(document.getElementById("form1").password.value==""){
            alert("请输入密码名！");
            document.getElementById("form1").password.focus();
            return false;
        }
    }
</script>
<body>
<div class="container">
    <div class="login-area">
        <span class="login-title">账号登录</span>

        <form method="POST" action="${pageContext.request.contextPath }/user" onsubmit="return check()" class="fm-login">
            <input type="hidden" name="method" value="login">

            <div class="fm-group"><span class="input-info">用户名</span><input type="text" placeholder="用户名" id="username" name="username" required="required"></div>
            <div class="fm-group"><span class="input-info">密码</span><input type="password" placeholder="密码" id="password" name="password" required="required"></div>
            <input type="submit" value="登录" class="btn-primary">


            <div class="bottom-content">
                <div class="link-register"><a id="register" href="Register">注册账号</a></div>
                <div class="link-forget"><a href="javascript:;">忘记密码</a></div>
            </div>
        </form>
    </div>
</div>
</body>

</body>
</html>
