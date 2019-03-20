package edu.xjtu.service;

import edu.xjtu.dao.UserDao;
import edu.xjtu.dao.UserDaoImple;
import edu.xjtu.vo.User;

public class UserService {

	public User login(User user) {
		UserDao dao = new UserDaoImple();
		return dao.login(user);
	}

	public Boolean regist(User user) {
		UserDao dao = new UserDaoImple();
		return dao.regist(user);
	}
	
	
}
