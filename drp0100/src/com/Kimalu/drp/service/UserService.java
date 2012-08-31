package com.Kimalu.drp.service;

import java.util.List;

import com.Kimalu.drp.dao.AbstractBaseDao;
import com.Kimalu.drp.daoImpl.UserDaoImpl;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.exception.PasswordNoCorrect;
import com.Kimalu.drp.exception.UserNoFoundException;

public class UserService {
	// µ¥Àý
	private static UserService userService = new UserService();

	private UserService() {

	}

	public static UserService getInstance() {
		return userService;
	}

	private AbstractBaseDao<User> userDao = UserDaoImpl.getInstance();

	public String addUser(User user) {
		if (null != queryById(user.getUserId())) {
			return "already exist";
		}
		return userDao.add(user);
	}

	public List<User> queryUser() {
		return userDao.query(null, null);
	}

	public List<User> queryUser(Integer pageNo, Integer pageSize) {
		return userDao.query(pageNo, pageSize);
	}

	public Page<User> queryUserListByPage(Page<User> page) {
		page.setList(this.queryUser(page.getCurrentPageNo(), page.getPageSize()));
		return page;
	}

	public User queryById(String userId) {
		User u = userDao.queryById(userId);
		return u;
	}

	public int getTotalNum() {
		return userDao.getTotalNum("t_user");
	}

	public void del(String[] userIds) {
		userDao.del(userIds);
	}

	public void updateUser(User u) {
		userDao.updateById(u);
	}
	public User login(String userId,String password) throws UserNoFoundException, PasswordNoCorrect{
		User u=this.queryById(userId);
		if(u==null){
			throw new UserNoFoundException(userId+"²»´æÔÚ");
		}else if(!password.equals(u.getPassword())){
			throw new PasswordNoCorrect("ÃÜÂë´íÎó");
		}
		return u;
	}
}
