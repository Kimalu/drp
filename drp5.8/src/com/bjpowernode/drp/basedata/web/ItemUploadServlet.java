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

	// ���ڴ����ʱ�ļ���Ŀ¼
	private File tempPath = null; 
	
	//�����ϴ��ļ���Ŀ¼
	private File uploadPath = null;
	
	@Override
	public void init() throws ServletException {
		//ȡ����ʱ����Ŀ¼
		tempPath = new File(this.getServletContext().getRealPath("temp"));
		//���Ŀ¼�����ڣ�����һ��
		if (!tempPath.exists()) {
			tempPath.mkdir();
		}
		//ȡ���ϴ�·��
		uploadPath = new File(this.getServletContext().getRealPath("upload"));
		//���Ŀ¼�����ڣ�����һ��
		if (!uploadPath.exists()) {
			uploadPath.mkdir();
		}
		//������ʾ���ø����init��������ʼ��ItemService
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
				// �������������ļ�������б���Ϣ
				if (!item.isFormField()) {
					String fileName = item.getName();
					fileName = fileName.substring(fileName.lastIndexOf("\\"), fileName.length());
					String fullFilePath = uploadPath + fileName;
					//���ϴ��ļ�д�����
					item.write(new File(fullFilePath));
					
					//���ϴ��ļ������Ʊ��浽���ݿ���
					//ItemService itemService = (ItemService)BeanFactory.getInstance().getBean(ItemService.class);
					itemService.modifyUploadFileNameField(itemNo, fileName);
					res.sendRedirect(req.getContextPath() + "/servlet/basedata/SearchItemServlet");
				}
			}	
		} catch (Exception e) {
			e.printStackTrace();
			throw new AppException("�ļ��ϴ�ʧ�ܣ�");
		}
	}
}