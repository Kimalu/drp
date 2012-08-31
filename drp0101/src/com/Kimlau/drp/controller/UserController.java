package com.Kimlau.drp.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.catalina.Session;

import net.sf.json.JSONArray;

import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.service.UserService;
import com.Kimalu.drp.util.JsonUtil;
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UserController() {
        super();
    }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		String command=request.getParameter("command");
		if("add".equals(command)){
			String message=addUser(request);
			if("success".equals(message)){
				request.getRequestDispatcher("/sysmgr/user_maint.jsp").forward(request, response);
			}else{
				request.setAttribute("error message", message);
				request.getRequestDispatcher("/sysmgr/error.jsp").forward(request, response);
			}
		}else if("updpw".equals(command)){
			//String userId=request.getParameter("userId");
			//User u=queryById(userId);
			User u=(User)request.getSession().getAttribute("user");
			u.setPassword(request.getParameter("newPassword"));
			this.updateUser(u);
			request.getSession().setAttribute("user", u);
			response.setCharacterEncoding("utf-8");
			response.getWriter().print("更改成功");
		}else if("del".equals(command)){
			String[] userIds=request.getParameterValues("selectFlag");
			delUser(userIds);
		}else if("update".equals(command)){
				updateUser(request);
				request.getRequestDispatcher("/sysmgr/user_maint.jsp").forward(request, response);
		}else if("query".equals(command)){
			Page<User> page=initPage(request);
			queryUserListByPage(page);
			if(page.getList()!=null){
				response.getWriter().print(getJson(page)); 
			}
		}else if("queryById".equals(command)){
			String userId=request.getParameter("userId");
			if(userId!=null && !"".equals(userId)){
				User user=this.queryById(userId);
				request.setAttribute("user", user);
				request.getRequestDispatcher("/sysmgr/user_maint.jsp").forward(request, response);
			}
		}
	}
	private Page<User> initPage(HttpServletRequest request){
		Page<User> page=new Page<User>();
		page.setTotalNum(this.getTotalNum()-1);
		page.setCurrentPageNo(1);
		String pageNo=request.getParameter("pageNo");
		if(pageNo!=null && !"".equals(pageNo)){
			page.setCurrentPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(4);
		int totalPageNum=(page.getPageSize()+page.getTotalNum())/page.getPageSize();
		page.setTotalPageNum(totalPageNum);
		return page;
	}
	
	
	private String getJson(Page<User> userPage){
		return JsonUtil.object2json(userPage);
	}
	private void queryUserListByPage(Page<User> page){
		UserService userService=UserService.getInstance();
		page.setList(userService.queryUser(page.getCurrentPageNo(),page.getPageSize()));
	}
	private void delUser(String[] userIds){
		UserService userService=UserService.getInstance();
		userService.del(userIds);
	}
	private String addUser(HttpServletRequest request){
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String contactTel=request.getParameter("contactTel");
		String email=request.getParameter("email");
		User user=new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setContactTel(contactTel);
		user.setEmail(email);
		user.setCreateDate(new Date());
		UserService userService=UserService.getInstance();
		return userService.addUser(user);
	}
	private User queryById(String userId){
		UserService userService=UserService.getInstance();
		return userService.queryById(userId);
	}
	private int getTotalNum(){
		UserService userService=UserService.getInstance();
		return userService.getTotalNum();
	}
	private void updateUser(HttpServletRequest request){
		String userId=request.getParameter("userId");
		String userName=request.getParameter("userName");
		String password=request.getParameter("password");
		String contactTel=request.getParameter("contactTel");
		String email=request.getParameter("email");
		User user=new User();
		user.setUserId(userId);
		user.setUserName(userName);
		user.setPassword(password);
		user.setContactTel(contactTel);
		user.setEmail(email);
		user.setCreateDate(new Date());
		this.updateUser(user);
	}
	private void updateUser(User user){
		UserService userService=UserService.getInstance();
		userService.updateUser(user);
	}
}
