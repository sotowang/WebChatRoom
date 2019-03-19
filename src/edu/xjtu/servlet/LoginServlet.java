package edu.xjtu.servlet;

import edu.xjtu.model.User;
import edu.xjtu.service.impl.UserServiceImpl;
import edu.xjtu.utils.ResponseInformation;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


/**
 * 登陆功能
 */
@WebServlet("/login")
public class LoginServlet extends JsonServlet{

    public static final String LOGINED_USER_SESSION_ATTR = "logined_user";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=utf-8");
        response.setCharacterEncoding("UTF-8");
        String loginData = ReadFromStream(req);

        try {
            JSONObject loginUserJson = new JSONObject(loginData);
            User user = new User();
            UserServiceImpl impl = new UserServiceImpl();
            user.readFromJson(loginUserJson);

            if (impl.authenticateUSer(user)) {
                PrintWriter writer = response.getWriter();
                writer.println(ResponseInformation.getSuccessInformation());
                writer.close();


                User loginedUser = impl.queryUserWithUserName(user.getUsername());
                req.getSession().setAttribute(LOGINED_USER_SESSION_ATTR, loginedUser);
            } else {//验证失败
                PrintWriter writer = response.getWriter();
                writer.println(ResponseInformation.getErrorInformation("用户名或密码错误"));
                writer.close();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }



    }
}
