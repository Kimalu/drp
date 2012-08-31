package com.Kimalu.drp.dao.sysconfig;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.Kimalu.drp.dao.BaseDao;
import com.Kimalu.drp.domain.sysconfig.SysMenu;

@Repository
public class SysMenuDao extends BaseDao<SysMenu>{
	
	public List<SysMenu> getMenuList(boolean isMainMenu){
		StringBuffer hql=new StringBuffer(" from SysMenu ");
		if(isMainMenu){
			hql.append(" where isMainMenu=true");
		}else{
			hql.append(" where isMainMenu=false");
		}
		return this.createQuery(hql.toString(), null).list();
	}

}
