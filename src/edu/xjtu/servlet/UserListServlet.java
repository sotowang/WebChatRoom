package edu.xjtu.servlet;

import edu.xjtu.controller.ChatController;
import edu.xjtu.model.User;
import edu.xjtu.service.impl.UserServiceImpl;
import edu.xjtu.utils.ResponseInformation;
import org.json.JSONArray;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;


/**
 * 在线用户列表
 */
@WebServlet("/userlist")
public class UserListServlet extends HttpServlet {


    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        List<ChatController> usersOnline = ChatController.getConnectedUsers();
        JSONArray allUsers = new JSONArray();
        UserServiceImpl impl = new UserServiceImpl();

        //将在线用户实例化放到Json数组中
        for (ChatController chatController : usersOnline) {
            User user = impl.queryUserWithUserName(chatController.getUser().getUsername());
            if (null != user) {
                user.setPassword(null);
                allUsers.put(user.toJson());
            }
        }
        PrintWriter writer = resp.getWriter();
        try {
            writer.println(allUsers.toString());
        } catch (Exception e) {
            writer.println(ResponseInformation.getErrorInformation("用户列表获取错误"));
        }finally {
            writer.close();
        }
    }




}
