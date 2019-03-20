package edu.xjtu.dao;

import edu.xjtu.vo.User;

public interface UserDao {

	public User login(User user);

	public boolean regist(User user);
}
