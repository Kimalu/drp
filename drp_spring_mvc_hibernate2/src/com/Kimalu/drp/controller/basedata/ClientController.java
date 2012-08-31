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
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.ClientLevel;
import com.Kimalu.drp.service.ClientService;
import com.Kimalu.drp.service.DataDictService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/basedata/ClientController")
public class ClientController {
	
	@Autowired
	private ClientService clientService;
	@Autowired
	private DataDictService dataDictService;
	
	@RequestMapping(params = "command=getClientRoot", method = RequestMethod.GET)
	@ResponseBody
	public String getClientTreeRoot() {
		return this.getJson(clientService.getClientTreeRoot());
	}
	
	@RequestMapping(params = "command=getClientTree", method = RequestMethod.GET)
	public void getClientTree(HttpServletResponse response) throws IOException {
		response.setContentType("text/xml");
		Writer writer = response.getWriter();
		writer.write(clientService.getClientTreeXML());
		writer.flush();
		writer.close();
	}
	@RequestMapping(params = "command=showClientNodeCRUD", method = RequestMethod.GET)
	public String showClientNodeCRUD(int id,ModelMap modelMap) throws IOException {
		Client c=clientService.getClientById(id);
		modelMap.addAttribute("client", c);
		return "basedata/client_node_crud";
	}
	@RequestMapping(params = "command=showClientRegionAddPage", method = RequestMethod.GET)
	public String showClientRegionAddPage(int id,ModelMap modelMap) throws IOException {
		modelMap.addAttribute("pid", id);
		return "basedata/client_node_add";
	}
	@RequestMapping(params = "command=doClientRegionAdd", method = RequestMethod.GET)
	public String doClientRegionAdd(int pid,String name,ModelMap modelMap){
		Client pClient=clientService.getClientById(pid);
		Client client=new Client();
		client.setName(name);
		client.setIsClient("N");
		client.setParentClient(pClient);
		clientService.addClient(client);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=delClientRegion", method = RequestMethod.GET)
	public String delClientRegion(int id){
		Client client=clientService.getClientById(id);
		clientService.delClientAndChildrenById(client);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showModifyRegionPage", method = RequestMethod.GET)
	public String showModifyRegionPage(int id,ModelMap modelMap){
		Client client=clientService.getClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_node_modify";
	}
	
	
	@RequestMapping(params = "command=doModifyRegion", method = RequestMethod.GET)
	public String doModifyRegion(int id,String name){
		Client client=clientService.getClientById(id);
		client.setName(name);
		clientService.modifyClient(client);
		return "basedata/client_maint";
	}
	
	@RequestMapping(params = "command=showAddClientPage", method = RequestMethod.GET)
	public String showAddClientPage(int id,ModelMap modelMap){
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
	public String showClientCRUD(int id,ModelMap modelMap){
		Client client =clientService.getClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_crud";
	}
	
	@RequestMapping(params = "command=showClientModifyPage", method = RequestMethod.GET)
	public String showClientModifyPage(int id,ModelMap modelMap){
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
	public String showClientDetailPage(int id,ModelMap modelMap){
		Client client =clientService.getClientById(id);
		modelMap.addAttribute("client", client);
		return "basedata/client_detail";
	}
	
	@RequestMapping(params = "command=delClient", method = RequestMethod.GET)
	public String delClient(int id){
		Client client=clientService.getClientById(id);
		clientService.delClientAndChildrenById(client);
		return "basedata/client_maint";
	}
	
	private String getJson(Object o) {
		return JsonUtil.object2json(o);
	}
}
