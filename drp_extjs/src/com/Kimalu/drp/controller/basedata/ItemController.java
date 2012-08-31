package com.Kimalu.drp.controller.basedata;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.ItemCategory;
import com.Kimalu.drp.domain.ItemUnit;
import com.Kimalu.drp.service.DataDictService;
import com.Kimalu.drp.service.ItemService;
import com.Kimalu.drp.util.JsonUtil;

@Controller
@RequestMapping("/basedata/ItemController")
public class ItemController {
	@Autowired
	private ItemService itemService;
	@Autowired
	private DataDictService dataDictService;

	public ItemService getItemService() {
		return itemService;
	}

	public void setItemServie(ItemService itemService) {
		this.itemService = itemService;
	}

	@RequestMapping(params = "command=getItemList", method = RequestMethod.GET)
	@ResponseBody
	public String getItemList(Page<Item> page,String queryByItemName,ModelMap modelMap) {
		if(queryByItemName==null || "".equals(queryByItemName)){
//			modelMap.addAttribute("queryItemName", "");
			return this.getJson(itemService.getItemList(page));
		}else{
			modelMap.addAttribute("queryItemName", queryByItemName);
			return this.getJson(itemService.queryItemListByName(page, queryByItemName));
		}
	}

	@RequestMapping(params = "command=getItemUnit", method = RequestMethod.GET)
	@ResponseBody
	public String getItemUnit() {
		return this.getJson(dataDictService.getDataDictList("unit"));
	}

	@RequestMapping(params = "command=getItemCatagory", method = RequestMethod.GET)
	@ResponseBody
	public String getItemCatagory() {
		return this.getJson(dataDictService.getDataDictList("category"));
	}

	@RequestMapping(params = "command=addItem", method = RequestMethod.POST)
	public String addItem(Item item, String category, String unit) throws Exception {
		item.setItemCategory((ItemCategory) dataDictService.getDataDict(category));
		item.setItemUnit((ItemUnit) dataDictService.getDataDict(unit));
		Item oldItem=itemService.queryItemByItemNo(item.getItemNo());
		if(oldItem!=null){
			throw new Exception("物料代码已存在");
		}else{
			itemService.addItem(item);
		}
		
		return "basedata/item_maint";
	}

	@RequestMapping(params = "command=del", method = RequestMethod.POST)
	public String del(String itemIds) {
		String[] itemArray = itemIds.split(",");
		for(String id:itemArray){
			Item item=itemService.getItem(id);
			String fileName=item.getUploadFileName();
			if(fileName!=null && !"".equals(fileName)){
				new File(item.getUploadFileName()).delete();
			}
		}
		itemService.delItems(itemArray);
		return "basedata/item_maint";
	}

	@RequestMapping(params = "command=showModify", method = RequestMethod.GET)
	public String showModify(String id, ModelMap modelMap) {
		Item item = itemService.getItem(id);
		modelMap.addAttribute("item", item);
		return "basedata/item_modify";
	}

	@RequestMapping(params = "command=doModify", method = RequestMethod.POST)
	public String doModify(Item item, String categoryId, String unitId) {
		item.setItemCategory((ItemCategory) dataDictService.getDataDict(categoryId));
		item.setItemUnit((ItemUnit) dataDictService.getDataDict(unitId));
		itemService.modifyItem(item);
		return "basedata/item_maint";
	}

	@RequestMapping(params = "command=showUploadPic4Item", method = RequestMethod.GET)
	public String showUploadPic4Item(String id, ModelMap modelMap) {
		Item item = itemService.getItem(id);
		modelMap.addAttribute("item", item);
		return "basedata/item_upload";
	}

	@RequestMapping(params = "command=doUploadPic4Item", method = RequestMethod.POST)
	public String doUploadPic4Item(String id, @RequestParam("file")  MultipartFile file,HttpServletRequest request,ModelMap modelMap) throws IOException {
		Item item = itemService.getItem(id);
		String path=request.getSession().getServletContext().getRealPath("/upload");
		String fileName=item.getItemNo()+".gif";
		item.setUploadFileName(path+"\\"+fileName);
		itemService.modifyItem(item);
		if (!file.isEmpty()) {
			SaveFileFromInputStream(file.getInputStream(),path,fileName);
		}
		modelMap.addAttribute("item", item);
		return "basedata/item_upload";
	}
	@RequestMapping(params = "command=showItemDetail", method = RequestMethod.GET)
	public String showItemDetail(String itemNo,ModelMap modelMap) throws IOException {
		Item item = itemService.queryItemByItemNo(itemNo);
		modelMap.addAttribute("item", item);
		return "basedata/item_detail";
	}
	
//	@RequestMapping(params = "command=queryByItemName", method = RequestMethod.POST)
//	public String queryByItemName(Page<Item> page,String queryItemName,ModelMap modelMap) throws IOException {
//		itemService.queryItemListByName(page, queryItemName);
//		modelMap.addAttribute("queryItemName", queryItemName);
//		return "basedata/item_maint";
//	}

	private String getJson(Object page) {
		return JsonUtil.object2json(page);
	}
	public void SaveFileFromInputStream(InputStream stream, String path,
			String filename) throws IOException {
		FileOutputStream fs = new FileOutputStream(path + "/" + filename);
		byte[] buffer = new byte[1024 * 1024];
		int bytesum = 0;
		int byteread = 0;
		while ((byteread = stream.read(buffer)) != -1) {
			bytesum += byteread;
			fs.write(buffer, 0, byteread);
			fs.flush();
		}
		fs.close();
		stream.close();
	}


}
