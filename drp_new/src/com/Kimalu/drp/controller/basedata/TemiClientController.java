package com.Kimalu.drp.controller.basedata;

import java.io.IOException;
import java.io.Writer;
import java.util.List;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.TemiClient;
import com.Kimalu.drp.domain.TemiClientType;
import com.Kimalu.drp.service.DataDictService;
import com.Kimalu.drp.service.basedata.RegionService;
import com.Kimalu.drp.service.basedata.TemiClientService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/basedata/TemiClientController")
public class TemiClientController {
	
	@Autowired
	private TemiClientService temiClientService;
	@Autowired
	private RegionService regionService;
	@Autowired
	private DataDictService dataDictService;
	
	@RequestMapping(params = "command=getTemiClientRoot", method = RequestMethod.GET)
	@ResponseBody
	public String getTemiClientTreeRoot() {
		return this.getJson(temiClientService.getTemiClientTreeRoot());
	}
	
	@RequestMapping(params = "command=getTemiClientTree", method = RequestMethod.GET)
	public void getTemiClientTree(HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		Writer writer = response.getWriter();
		writer.write(temiClientService.getTemiClientTreeXML());
		writer.flush();
		writer.close();
	}
	@RequestMapping(params = "command=showTemiClientNodeCRUD", method = RequestMethod.GET)
	public String showTemiClientNodeCRUD(String id,ModelMap modelMap) throws IOException {
		Region c=regionService.getRegionClientById(id);
		modelMap.addAttribute("client", c);
		return "basedata/temi_client_node_crud";
	}
	@RequestMapping(params = "command=showTemiClientRegionAddPage", method = RequestMethod.GET)
	public String showTemiClientRegionAddPage(String id,ModelMap modelMap) throws IOException {
		modelMap.addAttribute("pid", id);
		return "basedata/client_node_add";
	}
	@RequestMapping(params = "command=doTemiClientRegionAdd", method = RequestMethod.GET)
	public String doTemiClientRegionAdd(String pid,String name,ModelMap modelMap){
		TemiClient pTemiClient=temiClientService.getTemiClientById(pid);
		TemiClient temiClient=new TemiClient();
		temiClient.setName(name);
		temiClient.setIsClient("N");
		temiClient.setParentClient(pTemiClient);
		temiClientService.addTemiClient(temiClient);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=delTemiClientRegion", method = RequestMethod.GET)
	public String delTemiClientRegion(String id){
		TemiClient temiClient=temiClientService.getTemiClientById(id);
		temiClientService.delTemiClientAndChildrenById(temiClient);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showModifyRegionPage", method = RequestMethod.GET)
	public String showModifyRegionPage(String id,ModelMap modelMap){
		TemiClient temiClient=temiClientService.getTemiClientById(id);
		modelMap.addAttribute("temiClient", temiClient);
		return "basedata/client_node_modify";
	}
	
	
	@RequestMapping(params = "command=doModifyRegion", method = RequestMethod.GET)
	public String doModifyRegion(String id,String name){
		TemiClient temiClient=temiClientService.getTemiClientById(id);
		temiClient.setName(name);
		temiClientService.modifyTemiClient(temiClient);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showAddTemiClientPage", method = RequestMethod.GET)
	public String showAddTemiClientPage(String id,ModelMap modelMap){
		modelMap.addAttribute("id", id);
		return "basedata/temi_client_add";
	}
	@RequestMapping(params = "command=getTemiClientType", method = RequestMethod.GET)
	@ResponseBody
	public String getTemiClientType(){
		List<AbstractDataDict> tctList=dataDictService.getDataDictList(TemiClientType.class);
		return this.getJson(tctList);
	}
	@RequestMapping(params = "command=addTemiClient", method = RequestMethod.POST)
	public String addTemiClient(TemiClient temiClient){
		temiClient.setIsClient("Y");
		temiClientService.addTemiClient(temiClient);
		return "basedata/temi_client_node_crud";
	}
	
	
	@RequestMapping(params = "command=showTemiClientCRUD", method = RequestMethod.GET)
	public String showTemiClientCRUD(String id,ModelMap modelMap){
		TemiClient Temiclient =temiClientService.getTemiClientById(id);
		modelMap.addAttribute("temiClient", Temiclient);
		return "basedata/temi_client_crud";
	}
	
	@RequestMapping(params = "command=showTemiClientModifyPage", method = RequestMethod.GET)
	public String showTemiClientModifyPage(String id,ModelMap modelMap){
		TemiClient temiClient =temiClientService.getTemiClientById(id);
		modelMap.addAttribute("temiClient", temiClient);
		return "basedata/temi_client_modify";
	}
	
	@RequestMapping(params = "command=modifyTemiClient", method = RequestMethod.POST)
	public String modifyTemiClient(TemiClient temiClient){
		TemiClient oldTemiClient=temiClientService.getTemiClientById(temiClient.getId());
		oldTemiClient.setAddress(temiClient.getAddress());
		oldTemiClient.setContactPerson(temiClient.getContactPerson());
		oldTemiClient.setChlidClients(temiClient.getChlidClients());
//		oldClient.setClientId(client.getClientId());
		oldTemiClient.setContactTel(temiClient.getContactTel());
		oldTemiClient.setTemiClientType(temiClient.getTemiClientType());
		oldTemiClient.setName(temiClient.getName());
		oldTemiClient.setZipCode(temiClient.getZipCode());
		temiClientService.modifyTemiClient(oldTemiClient);
		return "basedata/temi_client_maint";
	}
	
	@RequestMapping(params = "command=showTemiClientDetailPage", method = RequestMethod.GET)
	public String showTemiClientDetailPage(String id,ModelMap modelMap){
		TemiClient temiClient =temiClientService.getTemiClientById(id);
		modelMap.addAttribute("temiClient", temiClient);
		return "basedata/temi_client_detail";
	}
	
	@RequestMapping(params = "command=delTemiClient", method = RequestMethod.GET)
	public String delTemiClient(String id){
		TemiClient temiClient=temiClientService.getTemiClientById(id);
		temiClientService.delTemiClientAndChildrenById(temiClient);
		return "basedata/temi_client_maint";
	}
	
	private String getJson(Object o) {
		return JsonUtil.object2json(o);
	}
}
