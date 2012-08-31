package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * ItemDao�ӿ�
 * @author Administrator
 *
 */
public interface ItemDao {

	/**
	 * �������
	 * @param conn
	 * @param item
	 */
	public void addItem(Connection conn, Item item);
	
	/**
	 * ����id��ѯ
	 * @param conn
	 * @param itemNo
	 * @return ������ڷ���Item���󣬷��򷵻�null
	 */
	public Item findItemById(Connection conn, String itemNo);
	
	/**
	 * ��ҳ��ѯ
	 * @param conn
	 * @param queryString ��ѯ���������ϴ�������ƣ�
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel findAllItem(Connection conn, String queryString, int pageNo, int pageSize);
	
	/**
	 * �޸�����
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Connection conn, Item item);
	
	/**
	 * �����ϴ����ļ�����
	 * @param conn
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(Connection conn, String itemNo, String uploadFileName);
}
