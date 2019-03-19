package edu.xjtu.servlet;

import edu.xjtu.model.User;
import edu.xjtu.service.impl.UserServiceImpl;
import edu.xjtu.utils.ResponseInformation;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * 注册
 */
@WebServlet("/register")
public class RegisterServlet extends JsonServlet {
    public static final String LOGINED_USER_SESSION_ATTR = "logined_user";

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter printWriter = resp.getWriter();
        String postData = ReadFromStream(req);
        if (postData == null) {
            String responseStr = ResponseInformation.getErrorInformation("数据主请求为空");
            printWriter.println(responseStr);
            printWriter.close();
            return;
        }
        try {
            JSONObject userJson = new JSONObject(postData);
            User user = new User();
            UserServiceImpl impl = new UserServiceImpl();
            user.readFromJson(userJson);
            try {
                User checkUser = impl.queryUserWithUserName(user.getUsername());
                if (checkUser != null) {
                    printWriter.println(ResponseInformation.getErrorInformation("用户名已存在"));
                    printWriter.close();
                    return;
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
            if (impl.registerUser(user)) {
                printWriter.println(ResponseInformation.getSuccessInformation());
                req.getSession().setAttribute(LOGINED_USER_SESSION_ATTR, user);
                printWriter.close();
                return;
            } else {
                printWriter.println(ResponseInformation.getErrorInformation("未知错误"));
                printWriter.close();
                return;
            }
        } catch (JSONException e) {
            e.printStackTrace();
            printWriter.println(ResponseInformation.getErrorInformation("系统异常错误" + e.getMessage()));
            printWriter.close();

        }

    }
}
