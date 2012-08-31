package com.Kimlau.drp.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.FileUploadException;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.service.ItemService;

public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    public UploadServlet() {
        super();
    }

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		this.doPost(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ItemService.getInstance().modifyItem(this.uploadPic(request));
		request.getRequestDispatcher("/basedata/ItemController?command=showList").forward(request, response);
	}
	
	public Item uploadPic(HttpServletRequest request) {
		Item item=new Item();
		String path = this.getServletContext().getRealPath("/upload");
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setRepository(new File(path));
		factory.setSizeThreshold(4096);
		factory.setSizeThreshold(1024 * 1024);
		ServletFileUpload sfu = new ServletFileUpload(factory);
		List<FileItem> lis;
		try {
			lis = sfu.parseRequest(request);
			for (FileItem fitem : lis) {
				if(fitem.isFormField()){
					String name = fitem.getFieldName();
					String value = fitem.getString();
					if("itemNo".equalsIgnoreCase(name)){
						item=ItemService.getInstance().getItem(value);
					}
				}
				if (!fitem.isFormField()) {
					String name = fitem.getFieldName();
					String value = fitem.getName();
					
					fitem.write(new File(path, item.getItemNo()+".gif"));
					item.setUploadFileName(item.getItemNo()+".gif");
				}
			}
		} catch (FileUploadException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return item;

	}
}
