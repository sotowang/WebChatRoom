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
    <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta content="width=device-width, initial-scale=1.0, maximum-scale=1.0, user-scalable=0" name="viewport">
    <title>用户注册</title>
    <link rel="stylesheet" type="text/css" href="asserts/css/register.css">
    <script src="asserts/js/jquery-2.1.4.min.js" type="text/javascript"></script>
    <script type="text/javascript" src="asserts/js/register.js"></script>
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
    <div class="register-area">
			<span class="register-title">
				用户注册
			</span>

        <center><h3 style="color:red;">${ msg }</h3></center>

        <form method="post" action="${pageContext.request.contextPath }/user" onsubmit="return check()" class="fm-register">
            <input type="hidden" name="method" value="regist">


            <div class="fm-group"><span class="input-info">用户名</span>
                <input type="text" placeholder="用户名" name="username" id="username" required="required" autocomplete="off"></div>
            <%--<div class="fm-group"><span class="input-info">昵称</span>--%>
                <%--<input type="text" placeholder="昵称" name="nickname" id="nickname" required="required"  autocomplete="off"></div>--%>
            <div class="fm-group"><span class="input-info">密码</span>
                <input type="password" id="password" name="password" placeholder="密码" required="required"  autocomplete="off"></div>
            <div class="operate-grid">
                <input type="submit" id="register" value="立即注册" class="btn btn-primary">
                <a class="notify">*请填写每一项</a>
            </div>
        </form>

    </div>
</div>
</body>
</html>
