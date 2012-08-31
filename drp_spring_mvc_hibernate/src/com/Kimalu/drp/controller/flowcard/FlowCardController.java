package com.Kimalu.drp.controller.flowcard;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.domain.flowcard.FlowCard;
import com.Kimalu.drp.domain.flowcard.FlowCardDetail;
import com.Kimalu.drp.service.ItemService;
import com.Kimalu.drp.service.basedata.ClientService;
import com.Kimalu.drp.service.flowcard.AimClientService;
import com.Kimalu.drp.service.flowcard.FlowCardService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/flowcard/FlowCardController")
public class FlowCardController {
	@Autowired
	private FlowCardService flowCardService;
	@Autowired
	private ClientService clientService;
	@Autowired
	private ItemService itemService;
	@Autowired
	private AimClientService aimClientService;
	
	@RequestMapping(params = "command=getClientList", method = RequestMethod.GET)
	@ResponseBody
	public String getClientList(Page<Client> page,String queryByClientName,ModelMap modelMap) {
		
		if(queryByClientName==null || "".equals(queryByClientName)){
//			modelMap.addAttribute("queryItemName", "");
			clientService.getClietList(page);
		}else{
			modelMap.addAttribute("queryByClientName", queryByClientName);
			clientService.getClietListByName(page, queryByClientName);
		}
		for(Client c:page.getList()){
			c.setChlidClients(null);
			c.setParentClient(null);
		}
		return this.getJson(page);
	}
	
	@RequestMapping(params = "command=showClientSelect", method = RequestMethod.GET)
	public String showClientSelect() {
		return "flowcard/client_select";
	}
	
	@RequestMapping(params = "command=clientSelected", method = RequestMethod.GET)
	public String clientSelected(String id,ModelMap modelMap) {
		Client c=clientService.getClientById(id);
		modelMap.addAttribute("client",c);
		return "flowcard/flow_card_add";
	}
	
	@RequestMapping(params = "command=showItemSelect", method = RequestMethod.GET)
	public String showItemSelect(int index,ModelMap modelMap) {
		modelMap.addAttribute("index",index);
		return "flowcard/item_select";
	}
	
	@RequestMapping(params = "command=getItemList", method = RequestMethod.GET)
	@ResponseBody
	public String getItemList(Page<Item> page,String queryByItemName,ModelMap modelMap) {
		
		if(queryByItemName==null || "".equals(queryByItemName)){
//			modelMap.addAttribute("queryItemName", "");
			itemService.getItemList(page);
		}else{
			modelMap.addAttribute("queryByClientName", queryByItemName);
			itemService.queryItemListByName(page, queryByItemName);
		}
		return this.getJson(page);
	}
	
	@RequestMapping(params = "command=itemSelected", method = RequestMethod.GET)
	public String itemSelected(String id,int index,ModelMap modelMap) {
		Item item=itemService.getItem(id);
		modelMap.addAttribute("index",index);
		modelMap.addAttribute("item",item);
		return "flowcard/flow_card_add";
	}
	@RequestMapping(params = "command=showAimClientSelect", method = RequestMethod.GET)
	public String showAimClientSelect(int index,ModelMap modelMap) {
		modelMap.addAttribute("index",index);
		return "flowcard/aim_client_select";
	}
	
	@RequestMapping(params = "command=getAimClientList", method = RequestMethod.GET)
	@ResponseBody
	public String getAimClientList(Page<Region> page,String queryByAimClientName,ModelMap modelMap) {
		if(queryByAimClientName==null || "".equals(queryByAimClientName)){
			aimClientService.getAimClientList(page);
		}else{
			modelMap.addAttribute("queryByClientName", queryByAimClientName);
			aimClientService.queryAimClientListByName(page, queryByAimClientName);
		}
		for(Region r:page.getList()){
			r.setChlidClients(null);
			r.setParentClient(null);
		}
		return this.getJson(page);
	}
	
	@RequestMapping(params = "command=addFlowCard", method = RequestMethod.POST)
	public String addFlowCard(HttpServletRequest request,String cid,String[] itid,String[] aid,String[] qty) {
		User recorder=(User)request.getSession().getAttribute("user");
		Client c= clientService.getClientById(cid);
		List<Item> itemList=new ArrayList<Item>();
		List<Region> aimClientList=new ArrayList<Region>();
		List<Double> qtyList=new ArrayList<Double>();
		for(String tid:itid){
			itemList.add(itemService.getItem(tid));
		}
		for(String aimId:aid){
			aimClientList.add(aimClientService.getAimClientbyId(aimId));
		}
		for(String q:qty){
			qtyList.add(Double.parseDouble(q));
		}
		flowCardService.addFlowCard(recorder,c,itemList,aimClientList,qtyList);
		return "flowcard/flow_card_maint";
	}
	
	@RequestMapping(params = "command=getFlowCardList", method = RequestMethod.GET)
	@ResponseBody
	public String getFlowCardList(Page<FlowCard> page) {
		return this.getJson(flowCardService.getFlowCardList(page));
	}
	
	@RequestMapping(params = "command=showFlowCardModifyPage", method = RequestMethod.GET)
	public String showFlowCardModifyPage(String fcid,ModelMap modelMap) {
		FlowCard fc=flowCardService.getFlowCardById(fcid);
		List<FlowCardDetail> fcdList=flowCardService.getFlowCardDetailList(fcid);
		modelMap.addAttribute("flowCard",fc);
		modelMap.addAttribute("fcdList", fcdList);
		return "flowcard/flow_card_modify";
	}
	
	@RequestMapping(params = "command=modifyFlowCard", method = RequestMethod.POST)
	public String modifyFlowCard(String fcId,String cid,String[] itid,String[] aid,String[] qty) {
		//User recorder=(User)request.getSession().getAttribute("user");
		Client c= clientService.getClientById(cid);
		List<Item> itemList=new ArrayList<Item>();
		List<Region> aimClientList=new ArrayList<Region>();
		List<Double> qtyList=new ArrayList<Double>();
		for(String tid:itid){
			itemList.add(itemService.getItem(tid));
		}
		for(String aimId:aid){
			aimClientList.add(aimClientService.getAimClientbyId(aimId));
		}
		for(String q:qty){
			qtyList.add(Double.parseDouble(q));
		}
		flowCardService.modifyFlowCard(fcId,c,itemList,aimClientList,qtyList);
		return "flowcard/flow_card_maint";
	}
	
	@RequestMapping(params = "command=submitToCheck", method = RequestMethod.GET)
	public String submitToCheck(String fcid) {
		FlowCard fc=flowCardService.getFlowCardById(fcid);
		fc.setVouSts(FlowCard.SUBMIT_CHECKING);
		flowCardService.updateFlowCard(fc);
		return "flowcard/flow_card_maint";
	}
	
	@RequestMapping(params = "command=showFlowCardDetail", method = RequestMethod.GET)
	public String showFlowCardDetail(String fcid,ModelMap modelMap) {
		FlowCard fc=flowCardService.getFlowCardById(fcid);
		List<FlowCardDetail> flowCardDetails=flowCardService.getFlowCardDetailList(fcid);
		modelMap.addAttribute("flowCard", fc);
		modelMap.addAttribute("flowCardDetails", flowCardDetails);
		return "flowcard/flow_card_detail";
	}
	private String getJson(Object o) {
		return JsonUtil.object2json(o);
	}
}
