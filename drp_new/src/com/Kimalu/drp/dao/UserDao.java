package com.Kimalu.drp.dao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.hibernate.Query;
import org.springframework.stereotype.Repository;

import com.Kimalu.drp.domain.User;
@Repository
public class UserDao extends PageBaseDao<User> {
	
	public Page<User> getUserList(Page<User> page ){
		String hql="from User c";
		return this.getByQuery(page,hql,null);
	}
	public void delUsers(String[] userIds){
		List<String> idList=new ArrayList<String>();
		for(String id:userIds){
			idList.add(id);
		}
//		Map<String,List<String>> paraMap=new HashMap<String,List<String>>();
//		paraMap.put("userIds", idList);
		String hql="delete from User u where u.userId in (:userIds)";
		int	count=this.getSession().createQuery(hql).setParameterList("userIds", idList).executeUpdate();
	}
	public void updateUser(User u){
		User x=this.getEntityById(u.getUserId());
		this.evict(x);
		this.update(u);
	}
}
