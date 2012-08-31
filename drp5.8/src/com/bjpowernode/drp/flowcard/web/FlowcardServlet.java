package com.bjpowernode.drp.flowcard.web;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.bjpowernode.drp.basedata.domain.AimClient;
import com.bjpowernode.drp.basedata.domain.Client;
import com.bjpowernode.drp.basedata.domain.FiscalYearPeriod;
import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.flowcard.domain.FlowCard;
import com.bjpowernode.drp.flowcard.domain.FlowCardDetail;
import com.bjpowernode.drp.flowcard.service.FlowCardService;
import com.bjpowernode.drp.sysmgr.domain.User;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.Constants;
import com.bjpowernode.drp.util.PageModel;
import com.bjpowernode.drp.util.TransactionHandler;

/**
 * 采用一个FlowcardServlet处理流向单相关的操作
 * @author Administrator
 *
 */
public class FlowcardServlet extends HttpServlet {

	private FlowCardService flowCardService;
	
	@Override
	public void init() throws ServletException {
		BeanFactory factory = (BeanFactory)this.getServletContext().getAttribute(BeanFactory.class.getName());
		flowCardService = (FlowCardService)factory.getBean(FlowCardService.class);
		
		//对flowCardService生成事务代理
		TransactionHandler transactionHandler = new TransactionHandler();
		//返回flowCardService的代理类
		flowCardService = (FlowCardService)transactionHandler.newProxyObject(flowCardService);
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String command = request.getParameter("command");
		if (Constants.ADD.equals(command)) {
			add(request, response);
		}else if (Constants.DEL.equals(command)){
			
		}else {
			search(request, response);
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request,response);
	}
	
	/**
	 * 添加流向单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	@SuppressWarnings("unchecked")
	private void add(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		int clientInnerId = Integer.parseInt(request.getParameter("clientInnerId"));
		String[] aimInnerIds = request.getParameterValues("aimInnerId");
		String[] itemNos = request.getParameterValues("itemNo");
		String[] qtys = request.getParameterValues("qty");
		FlowCard flowCard = new FlowCard();
		
		//操作类型
		flowCard.setOptType("D");
		
		//操作日期
		flowCard.setOptDate(new Date());
		
		//单据状态
		flowCard.setVouSts("N");
		
		//录入人
		User recorder = (User)request.getSession().getAttribute("login_user");
		flowCard.setRecorder(recorder);
		
		//供方分销商
		Client client = new Client();
		client.setId(clientInnerId);
		flowCard.setClient(client);
		
		//会计核算期
		FiscalYearPeriod fiscalYearPeriod = new FiscalYearPeriod();
		fiscalYearPeriod.setId(2);
		flowCard.setFiscalYearPeriod(fiscalYearPeriod);
		
		List flowCardDetailList = new ArrayList();
		//流向单明细信息
		for (int i=0; i<aimInnerIds.length; i++) {
			FlowCardDetail flowCardDetail = new FlowCardDetail();
			//需方客户
			AimClient aimClient = new AimClient();
			aimClient.setId(Integer.parseInt(aimInnerIds[i]));
			flowCardDetail.setAimClient(aimClient);
			
			//物料
			Item item = new Item();
			item.setItemNo(itemNos[i]);
			flowCardDetail.setItem(item);
			
			//操作数量
			flowCardDetail.setOptQty(Double.parseDouble(qtys[i]));
			
			//调整标记
			flowCardDetail.setAdjustFlag("N");
			flowCardDetailList.add(flowCardDetail);
		}
		
		flowCard.setFlowCardDetailList(flowCardDetailList);
		
		//调用添加方法
		flowCardService.addFlowCard(flowCard);
		
		//重定向
		//response.sendRedirect(request.getContextPath() + "/flowcard/flow_card_maint.html");
		response.sendRedirect(request.getContextPath() + "/servlet/flowcard/FlowcardServlet");
	}
	
	/**
	 * 查询流向单
	 * @param request
	 * @param response
	 * @throws ServletException
	 * @throws IOException
	 */
	private void search(HttpServletRequest request, HttpServletResponse response) 
	throws ServletException, IOException {
		String clientId = request.getParameter("clientId");
		String beginDate = request.getParameter("beginDate");
		if (beginDate == null) {
			beginDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		request.setAttribute("beginDate", beginDate);
		String endDate = request.getParameter("endDate");
		if (endDate == null) {
			endDate = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
		}
		request.setAttribute("endDate", endDate);
		int pageNo = 1;
		try {
			pageNo = Integer.parseInt(request.getParameter("pageNo"));
		}catch(NumberFormatException e){};
		
		int pageSize = Integer.parseInt(this.getServletContext().getInitParameter("page-size"));
		PageModel pageModel = flowCardService.findFlowCardList(pageNo, pageSize, clientId, beginDate, endDate);
		request.setAttribute("pageModel", pageModel);
		
		request.getRequestDispatcher("/flowcard/flow_card_maint.jsp").forward(request, response);
	}
}
