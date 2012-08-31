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

import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.AbstractDataDict;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.service.DataDictService;
import com.Kimalu.drp.service.basedata.ClientService;
import com.Kimalu.drp.service.basedata.RegionService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/basedata/ClientController")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private DataDictService dataDictService;
	@Autowired
	private RegionService regionService;
	
	@RequestMapping(params = "command=getClientRoot", method = RequestMethod.GET)
	@ResponseBody
	public String getClientTreeRoot() {
		return this.getJson(regionService.getClientTreeRoot());
	}
	
	@RequestMapping(params = "command=getClientTree", method = RequestMethod.GET)
	public void getClientTree(HttpServletResponse response) throws IOException {
		response.setContentType("text/xml;charset=UTF-8");
		Writer writer = response.getWriter();
		writer.write(regionService.getClientTreeXML());
		writer.flush();
		writer.close();
	}
	@RequestMapping(params = "command=showClientNodeCRUD", method = RequestMethod.GET)
	public String showClientNodeCRUD(String id,ModelMap modelMap) throws IOException {
		Region r=regionService.getRegionClientById(id);
		modelMap.addAttribute("client", r);
		return "basedata/client_node_crud";
	}
	@RequestMapping(params = "command=showClientRegionAddPage", method = RequestMethod.GET)
	public String showClientRegionAddPage(String id,ModelMap modelMap) throws IOException {
		modelMap.addAttribute("pid", id);
		return "basedata/client_node_add";
	}
	@RequestMapping(params = "command=doClientRegionAdd", method = RequestMethod.GET)
	public String doClientRegionAdd(String pid,String name,ModelMap modelMap){
		Region pClient=regionService.getRegionClientById(pid);
		Region client=new Region();
		client.setName(name);
		client.setIsClient("N");
		client.setParentClient(pClient);
		regionService.addRegion(client);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=delClientRegion", method = RequestMethod.GET)
	public String delClientRegion(String id){
		Region client=regionService.getRegionClientById(id);
		regionService.delRegionAndChildrenById(client);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showModifyRegionPage", method = RequestMethod.GET)
	public String showModifyRegionPage(String id,ModelMap modelMap){
		Region client=regionService.getRegionClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_node_modify";
	}
	
	
	@RequestMapping(params = "command=doModifyRegion", method = RequestMethod.GET)
	public String doModifyRegion(String id,String name){
		Region client=regionService.getRegionClientById(id);
		client.setName(name);
		regionService.modifyRegion(client);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showAddClientPage", method = RequestMethod.GET)
	public String showAddClientPage(String id,ModelMap modelMap){
		modelMap.addAttribute("id", id);
		return "basedata/client_add";
	}
	@RequestMapping(params = "command=getClientLevel", method = RequestMethod.GET)
	@ResponseBody
	public String getClientLevel(){
		List<AbstractDataDict> clList=dataDictService.getDataDictList("clientLevel");
		return this.getJson(clList);
	}
	@RequestMapping(params = "command=addClient", method = RequestMethod.POST)
	public String addClient(Client client){
		client.setIsClient("Y");
		clientService.addClient(client);
		return "basedata/client_node_crud";
	}
	
	
	@RequestMapping(params = "command=showClientCRUD", method = RequestMethod.GET)
	public String showClientCRUD(String id,ModelMap modelMap){
		Client client =clientService.getClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_crud";
	}
	
	@RequestMapping(params = "command=showClientModifyPage", method = RequestMethod.GET)
	public String showClientModifyPage(String id,ModelMap modelMap){
		Client client =clientService.getClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_modify";
	}
	
	@RequestMapping(params = "command=modifyClient", method = RequestMethod.POST)
	public String modifyClient(Client client){
		Client oldClient=clientService.getClientById(client.getId());
		oldClient.setAddress(client.getAddress());
		oldClient.setBankAcctNo(client.getBankAcctNo());
		oldClient.setChlidClients(client.getChlidClients());
//		oldClient.setClientId(client.getClientId());
		oldClient.setContactTel(client.getContactTel());
		oldClient.setClientLevel(client.getClientLevel());
		oldClient.setName(client.getName());
		oldClient.setZipCode(client.getZipCode());
		clientService.modifyClient(oldClient);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showClientDetailPage", method = RequestMethod.GET)
	public String showClientDetailPage(String id,ModelMap modelMap){
		Client client =clientService.getClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_detail";
	}
	
	@RequestMapping(params = "command=delClient", method = RequestMethod.GET)
	public String delClient(String id){
		Client client=clientService.getClientById(id);
		clientService.delClientAndChildrenById(client);
		return "basedata/client_maint";
	}
	
	private String getJson(Object o) {
		return JsonUtil.object2json(o);
	}
}
