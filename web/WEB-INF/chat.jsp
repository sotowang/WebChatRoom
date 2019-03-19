<%@ page import="edu.xjtu.model.User" %>
<%@ page import="edu.xjtu.servlet.LoginServlet" %>
<%--
  Created by IntelliJ IDEA.
  User: xjtuse
  Date: 19-3-19
  Time: 下午4:28
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%

    <%--获取session中的用户属性--%>
    Object sessionObj = request.getSession().getAttribute(LoginServlet.LOGINED_USER_SESSION_ATTR);
    User loginedUser = null;
    if (null != sessionObj) {
        if (sessionObj instanceof User) {
            loginedUser = (User) sessionObj;
        } else {
            <%--重定向页面--%>
            request.getRequestDispatcher("login.jsp").forward(request, response);
        }
    }
%>


<html>
<head>
    <title>聊天页面</title>
</head>
<body>


</body>
</html>
