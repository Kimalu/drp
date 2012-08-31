package com.Kimalu.drp.service.sysconfig;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.sysconfig.SysMenuDao;
import com.Kimalu.drp.domain.sysconfig.SysMenu;

@Service
public class SysMenuService {
	@Autowired
	private SysMenuDao smd;

	public SysMenuDao getSmd() {
		return smd;
	}

	public void setSmd(SysMenuDao smd) {
		this.smd = smd;
	}
	@Transactional(readOnly=true)
	public List<SysMenu> getMainMenu(){
		return smd.getMenuList(true);
	}

}
