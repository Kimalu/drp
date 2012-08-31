package com.Kimlau.drp.controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.ItemCategory;
import com.Kimalu.drp.domain.ItemUnit;
import com.Kimalu.drp.domain.Page;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.service.ItemService;
import com.Kimalu.drp.service.UserService;

public class ItemController extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public ItemController() {
		super();
	}

	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		String command=request.getParameter("command");
		if ("addItem".equalsIgnoreCase(command)) {
			List<AbstractDataDict> categoryList = new ArrayList<AbstractDataDict>();
			categoryList = ItemService.getInstance().getDataDictList("itemCategory");
			List<AbstractDataDict> unitList = new ArrayList<AbstractDataDict>();
			unitList = ItemService.getInstance().getDataDictList("itemUnit");
			request.setAttribute("itemUnit", unitList);
			request.setAttribute("itemCategory", categoryList);
			request.getRequestDispatcher("/basedata/item_add.jsp").forward(request, response);
		} else if ("add".equalsIgnoreCase(command)) {
			String itemNo = request.getParameter("itemNo");
			String itemName = request.getParameter("itemName");
			String spec = request.getParameter("spec");
			String pattern = request.getParameter("pattern");
			String category = request.getParameter("category");
			String unit = request.getParameter("unit");
			ItemUnit itemUnit = new ItemUnit();
			itemUnit.setId(unit);
			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setId(category);
			Item item = new Item();
			item.setItemNo(itemNo);
			item.setItemName(itemName);
			if (spec != null && !"".equals(spec.trim())) {
				item.setSpec(spec);
			}
			if (pattern != null && !"".equals(pattern.trim())) {
				item.setPattern(pattern);
			}
			item.setItemUnit(itemUnit);
			item.setItemCategory(itemCategory);
			try {
				ItemService.getInstance().addItem(item);
				request.getRequestDispatcher("/basedata/ItemController?command=showList").forward(request, response);
			} catch (SQLException e) {
				System.out.println(e.getErrorCode());
				e.printStackTrace();
			}
		} else if ("showList".equalsIgnoreCase(command)) {
			Page<Item> page = initPage(request);
			ItemService.getInstance().getItemList(page);
			request.setAttribute("page", page);
			request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request, response);
		} else if ("itemDetail".equalsIgnoreCase(command)) {
			String itemNo=request.getParameter("itemNo");
			Item item=ItemService.getInstance().getItem(itemNo);
			request.setAttribute("item", item);
			request.getRequestDispatcher("/basedata/item_detail.jsp").forward(request, response);
		} else if ("upload".equalsIgnoreCase(command)) {
			String itemNo = request.getParameterValues("selectFlag")[0];
			Item item = ItemService.getInstance().getItem(itemNo);
			request.setAttribute("item", item);
			request.getRequestDispatcher("/basedata/item_upload.jsp").forward(
					request, response);
		} else if("queryByItemName".equalsIgnoreCase(command)){
			//System.out.println("queryByItemName");
			String queryItemName=request.getParameter("queryItemName");
			Page<Item> page = initPage(request);
			ItemService.getInstance().getItemListByQueryItemName(page, queryItemName);
			request.setAttribute("page", page);
			request.setAttribute("queryItemName", queryItemName);
			request.getRequestDispatcher("/basedata/item_maint.jsp").forward(request, response);
		}else if("showModify".equalsIgnoreCase(command)){
			List<AbstractDataDict> categoryList =ItemService.getInstance().getDataDictList("itemCategory");
			List<AbstractDataDict> unitList = ItemService.getInstance().getDataDictList("itemUnit");
			request.setAttribute("unitList", unitList);
			request.setAttribute("categoryList", categoryList);
			String itemNo=request.getParameter("itemNo");
			Item item = ItemService.getInstance().getItem(itemNo);
			request.setAttribute("item", item);
			request.getRequestDispatcher("/basedata/item_modify.jsp").forward(request, response);
		}else if("modify".equalsIgnoreCase(command)){
			String itemNo = request.getParameter("itemNo");
			String itemName = request.getParameter("itemName");
			String spec = request.getParameter("spec");
			String pattern = request.getParameter("pattern");
			String category = request.getParameter("category");
			String unit = request.getParameter("unit");
			ItemUnit itemUnit = new ItemUnit();
			itemUnit.setId(unit);
			ItemCategory itemCategory = new ItemCategory();
			itemCategory.setId(category);
			Item item = new Item();
			item.setItemNo(itemNo);
			item.setItemName(itemName);
			if (spec != null && !"".equals(spec.trim())) {
				item.setSpec(spec);
			}
			if (pattern != null && !"".equals(pattern.trim())) {
				item.setPattern(pattern);
			}
			item.setItemUnit(itemUnit);
			item.setItemCategory(itemCategory);
			ItemService.getInstance().modifyItem(item);
			request.getRequestDispatcher("/basedata/ItemController?command=showList").forward(request, response);
		}else if("del".equalsIgnoreCase(command)){
			String itemNo=request.getParameter("itemNo");
			ItemService.getInstance().del(itemNo);
			request.getRequestDispatcher("/basedata/ItemController?command=showList").forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		this.doGet(request, response);
	}

	private Page<Item> initPage(HttpServletRequest request) {
		Page<Item> page = new Page<Item>();
		if(request.getParameter("queryItemName")==null){
			page.setTotalNum(this.getTotalNum(null));
		}else{
			page.setTotalNum(this.getTotalNum(request.getParameter("queryItemName")));
		}
		page.setCurrentPageNo(1);
		String pageNo = request.getParameter("pageNo");
		if (pageNo != null && !"".equals(pageNo)) {
			page.setCurrentPageNo(Integer.parseInt(pageNo));
		}
		page.setPageSize(Integer.parseInt(this.getServletConfig()
				.getInitParameter("pageSize")));
		int totalPageNum = (page.getPageSize() + page.getTotalNum())
				/ page.getPageSize();
		page.setTotalPageNum(totalPageNum);
		return page;
	}

	private void queryUserListByPage(Page<User> page) {
		UserService userService = UserService.getInstance();
		page.setList(userService.queryUser(page.getCurrentPageNo(),
				page.getPageSize()));
	}

//	private int getTotalNum() {
//		ItemService itemService = ItemService.getInstance();
//		return itemService.getTotalNum();
//	}
	private int getTotalNum(String queryString){
		ItemService itemService = ItemService.getInstance();
		return itemService.getTotalNum(queryString);
	}

}
