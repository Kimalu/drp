package com.Kimalu.drp.controller.sysmgr;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.domain.sysconfig.SysMenu;
import com.Kimalu.drp.service.sysconfig.SysMenuService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/sysmgr/SysMenuController")
public class SysMenuController {
	@Autowired
	private SysMenuService sysMenuService;

	public SysMenuService getSysMenuService() {
		return sysMenuService;
	}

	public void setSysMenuService(SysMenuService sysMenuService) {
		this.sysMenuService = sysMenuService;
	}
	
	@RequestMapping(params="command=getSysMainMenuList",method = RequestMethod.GET)
	@ResponseBody
	public String getSysMainMenuList(){
		List<SysMenu> sysMenuList= sysMenuService.getMainMenu();
		String json= getJson(sysMenuList);
//		return "{"+json+"}";
		return json;
	}
	
	private String getJson(List<SysMenu> sysMenuList){
		return JsonUtil.object2json(sysMenuList);
	}
}
