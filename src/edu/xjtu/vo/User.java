package edu.xjtu.vo;

import edu.xjtu.model.IJsonSerialize;
import org.json.JSONObject;

import java.util.Map;

import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;


public class User implements HttpSessionBindingListener, IJsonSerialize {

	public static final String USERNAME = "username";
	public static final String PASSWORD  = "password";
	public static final String TYPE = "type";
	public static final String ID = "id";

	private int id;
	private String username;
	private String password;
	private String type;

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + id;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		User other = (User) obj;
		if (id != other.id)
			return false;
		return true;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	
	public void valueBound(HttpSessionBindingEvent event) {
		System.out.println("进入了....");
		HttpSession session = event.getSession();

		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
				.getServletContext().getAttribute("userMap");

		userMap.put(this, session);

	}

	// 当session和对象解除绑定的时候
	public void valueUnbound(HttpSessionBindingEvent event) {
		System.out.println("退出了....");
		HttpSession session = event.getSession();
		// 获得人员列表
		Map<User, HttpSession> userMap = (Map<User, HttpSession>) session
				.getServletContext().getAttribute("userMap");
		// 将用户移除了
		userMap.remove(this);
	}


	@Override
	public String toString() {
		return "User{" +
				"id=" + id +
				", username='" + username + '\'' +
				", password='" + password + '\'' +
				", type='" + type + '\'' +
				'}';
	}

	//将字段转换 为JSON格式存储
	public JSONObject toJson() {
		JSONObject jsonObject = new JSONObject();
		jsonObject.put(USERNAME, username);
		jsonObject.put(PASSWORD, password);
		jsonObject.put(TYPE, type);
		jsonObject.put(ID, id);

		return jsonObject;
	}

	public User() {
		super();
	}

	public User(String username, String password, String type) {

		this.username = username;
		this.password = password;
		this.type = type;
	}

	//将对象属性从JSON读出
	public void readFromJson(JSONObject jsonObject) {
		if (jsonObject.has(USERNAME)) {
			this.username = jsonObject.getString(USERNAME);
		}
		if (jsonObject.has(PASSWORD)) {
			this.password = jsonObject.getString(PASSWORD);
		}
		if (jsonObject.has(TYPE)) {
			this.type = jsonObject.getString(TYPE);
		}
		if (jsonObject.has(ID)) {
			this.id = Integer.valueOf(jsonObject.getString(ID));
		}
	}

}
