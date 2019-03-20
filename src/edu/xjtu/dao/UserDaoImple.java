package edu.xjtu.dao;

import edu.xjtu.utils.JDBCUtils;
import edu.xjtu.vo.User;
import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;

import java.sql.SQLException;

public class UserDaoImple implements UserDao {

	public User login(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql = "select * from user where username = ? and password = ?";
		User existUser;
		try {
			existUser = queryRunner.query(sql, new BeanHandler<User>(User.class), user.getUsername(),user.getPassword());
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("用户登录失败!");
		}
		return existUser;
	}

	public boolean regist(User user) {
		QueryRunner queryRunner = new QueryRunner(JDBCUtils.getDataSource());
		String sql1 = "select * from user where username = ?";

		try {
			if (null != queryRunner.query(sql1, new BeanHandler<User>(User.class), user.getUsername())) {
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

		String sql = "insert into user values(?,?,?,?)";
		Object[] params = {null,user.getUsername(),user.getPassword(),"user"};
		try {
			return queryRunner.update(sql, params) > 0;
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("用户注册失败!");
		}
	}

	public static void main(String[] args) {
		UserDaoImple userDaoImple = new UserDaoImple();
		User user = new User();
		user.setUsername("test");
		user.setPassword("test");
//		System.out.println(userDaoImple.regist(user));
		//

		System.out.println(userDaoImple.login(user));
	}


}
