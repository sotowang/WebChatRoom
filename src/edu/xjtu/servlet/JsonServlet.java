package edu.xjtu.servlet;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;

/**
 * 将request中JSON格式的信息读取成字符串
 */
public class JsonServlet extends HttpServlet {

    public String ReadFromStream(HttpServletRequest request) {
        try {

            //获取流
            BufferedReader bufferedReader = request.getReader();
            //缓冲区
            char[] tmpbuf = new char[5 * 1024];

            StringBuffer buffer = new StringBuffer();
            while (bufferedReader.read(tmpbuf) != -1) {
                buffer.append(tmpbuf);
            }
            return buffer.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
