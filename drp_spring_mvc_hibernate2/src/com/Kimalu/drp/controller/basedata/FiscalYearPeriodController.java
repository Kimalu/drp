package com.Kimalu.drp.controller.basedata;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.propertyeditors.CustomDateEditor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.FiscalYearPeriod;
import com.Kimalu.drp.service.FiscalYearPeriodService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/basedata/FypController")
public class FiscalYearPeriodController {
	
	@InitBinder
    public void initBinder(WebDataBinder binder) {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        dateFormat.setLenient(false);
        binder.registerCustomEditor(Date.class, new CustomDateEditor(dateFormat, false));
    }
	
	@Autowired
	private FiscalYearPeriodService fypService;
	
	public FiscalYearPeriodService getFypService() {
		return fypService;
	}

	public void setFypService(FiscalYearPeriodService fypService) {
		this.fypService = fypService;
	}

	@RequestMapping(params="command=getFYPList",method = RequestMethod.GET)
	@ResponseBody
	public String getFYPList(Page<FiscalYearPeriod> page){
		return this.getJson(fypService.getFYPList(page));
	}
	@RequestMapping(params="command=addFYP",method = RequestMethod.POST)
	public String addFYP(FiscalYearPeriod fyp){
		if(fyp.getPeriodStatus()==null){
			fyp.setPeriodStatus("n");
		}else{
			fyp.setPeriodStatus("y");
		}
		fypService.addFiscalYearPeriod(fyp);
		return "basedata/fiscal_year_period_maint";
	}
	@RequestMapping(params="command=showModify",method = RequestMethod.GET)
	public String showModify(int id,ModelMap modelMap){
		FiscalYearPeriod fyp=fypService.get(id);
		modelMap.addAttribute("fyp", fyp);
		return "basedata/fiscal_year_period_modify";
	}
	@RequestMapping(params="command=doModify",method = RequestMethod.POST)
	public String doModify(FiscalYearPeriod fyp){
		FiscalYearPeriod fypOld=fypService.get(fyp.getId());
		fypOld.setFiscalYear(fyp.getFiscalYear());
		fypOld.setFiscalMonth(fyp.getFiscalMonth());
		fypOld.setBeginDate(fyp.getBeginDate());
		fypOld.setEndDate(fyp.getEndDate());
		if(fyp.getPeriodStatus()==null){
			fypOld.setPeriodStatus("n");
		}else{
			fypOld.setPeriodStatus("y");
		}
		fypService.modifyFYP(fypOld);
		return "basedata/fiscal_year_period_maint";
	}
	
//	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		this.doPost(request, response);
//	}
//	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//		request.setCharacterEncoding("utf-8");
//		response.setContentType("utf-8");
//		String command=request.getParameter("command");
//		
//		if("add".equals(command)){
//			String message=addFyp(request);
//			if("success".equals(message)){
//				request.getRequestDispatcher("/basedata/fiscal_year_period_maint.jsp").forward(request, response);
//			}else{
//				request.setAttribute("error message", message);
//				request.getRequestDispatcher("/basedata/error.jsp").forward(request, response);
//			}
//		}else if("del".equals(command)){
//			
//		}else if("update".equals(command)){
//			
//		}else if("query".equals(command)){
//			Page<FiscalYearPeriod> page=initPage(request);
//			queryFypListByPage(page);
//			if(page.getList()!=null){
//				response.getWriter().print(getJson(page)); 
//			}
//		}else if("queryById".equals(command)){
//			
//		}
//	}
//	
	private String getJson(Page<FiscalYearPeriod> Page){
		return JsonUtil.object2json(Page);
	}
//	
//	private Page<FiscalYearPeriod> initPage(HttpServletRequest request){
//		Page<FiscalYearPeriod> page=new Page<FiscalYearPeriod>();
//		page.setTotalNum(this.getTotalNum());
//		page.setCurrentPageNo(1);
//		String pageNo=request.getParameter("pageNo");
//		if(pageNo!=null && !"".equals(pageNo)){
//			page.setCurrentPageNo(Integer.parseInt(pageNo));
//		}
//		page.setPageSize(4);
//		int totalPageNum=(page.getPageSize()+page.getTotalNum())/page.getPageSize();
//		page.setTotalPageNum(totalPageNum);
//		return page;
//	}
//	
//	private List<FiscalYearPeriod> queryFypListByPage(Page<FiscalYearPeriod> p) {
//		FiscalYearPeriodService fypService=FiscalYearPeriodService.getInstance();
//		return fypService.queryUserListByPage(p).getList();
//	}
//	private String getFypListJson(List<FiscalYearPeriod> fypList) {
//		return "{"+JsonUtil.list2json(fypList)+"}";
//	}
//	private String addFyp(HttpServletRequest request){
//		String periodSts="";
//		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");
//		String fiscalYear=request.getParameter("fiscalYear");
//		String fiscalMonth=request.getParameter("fiscalMonth");
//		String beginDate=request.getParameter("beginDate");
//		String endDate=request.getParameter("endDate");
//		if(request.getParameter("periodSts")==null){
//			periodSts="n";
//		}else{
//			periodSts="y";
//		}
//		FiscalYearPeriod fyp=new FiscalYearPeriod();
//		fyp.setFiscalYear(Integer.parseInt(fiscalYear));
//		fyp.setFiscalmonth(Integer.parseInt(fiscalMonth));
//		try {
//			fyp.setBeginDate(sdf.parse(beginDate));
//			fyp.setEndDate(sdf.parse(endDate));
//		} catch (ParseException e) {
//			System.out.println("日期格式错误");
//			e.printStackTrace();
//		}
//		fyp.setPeriodStatus(periodSts);
//		FiscalYearPeriodService fypService=FiscalYearPeriodService.getInstance();
//		return fypService.addFiscalYearPeriod(fyp);
//	}
//	private int getTotalNum(){
//		FiscalYearPeriodService fypService=FiscalYearPeriodService.getInstance();
//		return fypService.getTotalNum();
//	}

}
