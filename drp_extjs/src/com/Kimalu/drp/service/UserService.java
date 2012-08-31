package com.Kimalu.drp.service;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.UserDao;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.exception.AppException;
import com.Kimalu.drp.exception.PasswordNoCorrect;
import com.Kimalu.drp.exception.UserNoFoundException;
@Service
public class UserService {
	@Autowired
	private UserDao userDao;

	public UserDao getUserDao() {
		return userDao;
	}

	public void setUserDao(UserDao userDao) {
		this.userDao = userDao;
	}
	@Transactional
	public void addUser(User user){
		Assert.notNull(user, "用户对象不能为空");
		user.setCreateDate(new Date());
		User u=getUser(user.getUserId());
		if(u!=null){
			throw new AppException("用户代码不能重复");
		}
		userDao.save(user);
	}
	@Transactional
	public User getUser(String id){
		return userDao.getEntityById(id);
	}
	@Transactional
	public Page<User> getUserList(Page<User> page){
		return userDao.getUserList(page);
	}
	@Transactional
	public void modifyUser(User u){
		User user=userDao.getEntityById(u.getUserId());
		u.setCreateDate(user.getCreateDate());
		userDao.updateUser(u);
	}
	@Transactional
	public void deleteUsers(String[] userIds){
		userDao.delUsers(userIds);
	}
	@Transactional
	public User login(String userId,String password) throws UserNoFoundException, PasswordNoCorrect{
		User u=this.getUser(userId);
		if(u==null){
			throw new UserNoFoundException(userId+"不存在");
		}else if(!password.equals(u.getPassword())){
			throw new PasswordNoCorrect("密码错误");
		}
		return u;
	}
}
