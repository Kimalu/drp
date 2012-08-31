package com.Kimalu.drp.controller.sysmgr;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.exception.PasswordNoCorrect;
import com.Kimalu.drp.exception.UserNoFoundException;
import com.Kimalu.drp.service.UserService;
import com.Kimalu.drp.util.JsonUtil;
@Controller
@RequestMapping("/sysmgr/UserController")
public class UserController{
	@Autowired
	private UserService userService;
	
	public UserService getUserService() {
		return userService;
	}
	public void setUserService(UserService userService) {
		this.userService = userService;
	}
	@RequestMapping(params="command=add",method = RequestMethod.POST)
	public String addUser(User user){
		userService.addUser(user);
		return "sysmgr/user_maint";
	}
	@RequestMapping(params="command=getUserList",method = RequestMethod.GET)
	@ResponseBody
	public String getUserList(Page<User> page){
		userService.getUserList(page);
//		if(page.getList()!=null){
//			modelMap.addAttribute(page);
//		}
		String json= getJson(page);
		return json;
	}
	
	@RequestMapping(params="command=login",method = RequestMethod.POST)
	public String login(HttpServletRequest request, String userId,String password, ModelMap modelMap){
		try{
			User user=userService.login(userId,password);
			request.getSession().setAttribute("user", user);
		}catch(UserNoFoundException unfe){
			unfe.printStackTrace();
//			out.println(unfe.getMessage());
		}catch(PasswordNoCorrect pnc){
			pnc.printStackTrace();
//			out.println(pnc.getMessage());
		}
		return "main";
	}
	@RequestMapping(params="command=showModify",method = RequestMethod.POST)
	public String showModify(String userId, ModelMap modelMap){
			User user=userService.getUser(userId);
			modelMap.addAttribute("user", user);
		return "sysmgr/user_modify";
	}
	@RequestMapping(params="command=doModify",method = RequestMethod.POST)
	public String doModify(User user){
			userService.modifyUser(user);
		return "sysmgr/user_maint";
	}
	
	@RequestMapping(params="command=del",method = RequestMethod.POST)
	public String del(String userIds){
		String[] userArray=userIds.split(",");
		userService.deleteUsers(userArray);
		return "sysmgr/user_maint";
	}
	
	@RequestMapping( params="command=modifyPw",method = RequestMethod.GET)
	@ResponseBody
	public String modifyPw(HttpServletRequest request,String oldPassword,String newPassword){
		User user =(User)request.getSession().getAttribute("user");
		if(user.getPassword().equals(oldPassword)){
			user.setPassword(newPassword);
			userService.modifyUser(user);
			request.getSession().setAttribute("user", user);
			return "success";
		}
			return "pwerror";
	}
	
	private String getJson(Page<User> userPage){
		return JsonUtil.object2json(userPage);
	}

}
