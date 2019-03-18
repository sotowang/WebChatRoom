package edu.xjtu.utils;

import org.json.JSONObject;

/**
 * 服务器回收收到消息的状态
 */
public class ResponseInformation {
    public static String getSuccessInformation() {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("status", "succcess");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    /**
     * 钼是接收浏览器发送消息内容失败，且知原因
     * @param reason
     * @return
     */
    public static String getErrorInformation(String reason) {
        JSONObject jsonObject = new JSONObject();

        try {
            jsonObject.put("status", "error");
            jsonObject.put("reason", reason);
            return jsonObject.toString();
        } catch (Exception e) {
            e.printStackTrace();
        }

        return jsonObject.toString();
    }

    /**
     *钼是接收浏览器发送消息内容失败，且不知原因
     * @param e
     * @return
     */
    public static String getErrorInformation(Exception e) {
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("status", "error");
            jsonObject.put("reason", e.getMessage());
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return jsonObject.toString();
    }

}
