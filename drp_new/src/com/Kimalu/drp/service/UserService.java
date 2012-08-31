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
		Assert.notNull(user, "�û�������Ϊ��");
		user.setCreateDate(new Date());
		User u=getUser(user.getUserId());
		if(u!=null){
			throw new AppException("�û����벻���ظ�");
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
			throw new UserNoFoundException(userId+"������");
		}else if(!password.equals(u.getPassword())){
			throw new PasswordNoCorrect("�������");
		}
		return u;
	}
}
