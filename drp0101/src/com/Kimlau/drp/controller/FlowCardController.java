package com.Kimlau.drp.controller;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Kimalu.drp.daoImpl.ClientDaoImpl;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.service.ClientService;
import com.Kimalu.drp.service.ItemService;
import com.Kimalu.drp.service.UserService;

public class FlowCardController extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		String command = request.getParameter("command");
		if ("add".equalsIgnoreCase(command)) {

		} else if ("showClientList".equalsIgnoreCase(command)) {

		} else if ("selectClient".equalsIgnoreCase(command)) {
			ClientService clientservice = ClientService.getInstance();
			Page<Client> page = initPage(request);
			page.setList(clientservice.getClientList());
			request.setAttribute("page", page);
			request.getRequestDispatcher("/flowcard/client_select.jsp")
					.forward(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private Page<Client> initPage(HttpServletRequest request) {
		Page<Client> page = new Page<Client>();
		if (request.getParameter("queryItemName") == null) {
			page.setTotalNum(this.getTotalNum(null));
		} else {
			page.setTotalNum(this.getTotalNum(request
					.getParameter("queryItemName")));
		}
		page.setCurrentPageNo(1);
		String pageNo = request.getParameter("pageNo");
		if (pageNo != null && !"".equals(pageNo)) {
			page.setCurrentPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(Integer.parseInt(this.getServletContext()
				.getInitParameter("pageSize")));
		int totalPageNum = (page.getPageSize() + page.getTotalNum())
				/ page.getPageSize();
		page.setTotalPageNum(totalPageNum);
		return page;
	}

	// private int getTotalNum() {
	// ItemService itemService = ItemService.getInstance();
	// return itemService.getTotalNum();
	// }
	private int getTotalNum(String queryString) {
		ClientService clientService = ClientService.getInstance();
		return clientService.getTotalNum(queryString);
	}

}
