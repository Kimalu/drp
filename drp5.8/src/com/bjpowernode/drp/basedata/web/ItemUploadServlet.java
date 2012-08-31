package com.bjpowernode.drp.basedata.web;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.fileupload.*;
import java.util.*;
import java.util.regex.*;
import java.io.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;

import com.bjpowernode.drp.basedata.manager.ItemService;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.BeanFactory;

public class ItemUploadServlet extends AbstractItemServlet {

	// 用于存放临时文件的目录
	private File tempPath = null; 
	
	//保存上传文件的目录
	private File uploadPath = null;
	
	@Override
	public void init() throws ServletException {
		//取得临时交换目录
		tempPath = new File(this.getServletContext().getRealPath("temp"));
		//如果目录不存在，创建一个
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		//取得上传路径
		uploadPath = new File(this.getServletContext().getRealPath("upload"));
		//如果目录不存在，创建一个
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		//必须显示调用父类的init方法来初始化ItemService
		super.init();
	}

	@SuppressWarnings({ "unused", "unchecked" })
	public void doPost(HttpServletRequest req, HttpServletResponse res)
			throws ServletException, IOException {
		DiskFileItemFactory factory = new DiskFileItemFactory();
		factory.setSizeThreshold(4096);
		factory.setRepository(tempPath);
		ServletFileUpload upload = new ServletFileUpload(factory);
		upload.setSizeMax(1000000);
		try {
			List fileItems = upload.parseRequest(req);
			Iterator iter = fileItems.iterator();
			String itemNo = "";
			while (iter.hasNext()) {
				FileItem item = (FileItem) iter.next();
				if (item.isFormField()) {
					if ("itemNo".equals(item.getFieldName())) {
						itemNo = item.getString();
					}
				}
				// 忽略其他不是文件域的所有表单信息
				if (!item.isFormField()) {
					String fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\"), fileName.length());
					String fullFilePath = uploadPath + fileName;
					//将上传文件写入磁盘
					item.write(new File(fullFilePath));
					
					//将上传文件的名称保存到数据库中
					//ItemService itemService = (ItemService)BeanFactory.getInstance().getBean(ItemService.class);
					itemService.modifyUploadFileNameField(itemNo, fileName);
					res.sendRedirect(req.getContextPath() + "/servlet/basedata/SearchItemServlet");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("文件上传失败！");
		}
	}
}