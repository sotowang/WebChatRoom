package edu.xjtu.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * 这个是登出
 */

@WebServlet("/logout")
public class LogoutServlet extends JsonServlet{
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        req.getSession().removeAttribute(LoginServlet.LOGINED_USER_SESSION_ATTR);

    }
}
