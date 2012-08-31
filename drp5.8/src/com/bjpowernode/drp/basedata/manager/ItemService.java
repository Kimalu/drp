package com.bjpowernode.drp.basedata.manager;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * ItemService�ӿڶ���
 * @author Administrator
 *
 */
public interface ItemService {

	/**
	 * �������
	 * @param item
	 */
	public void addItem(Item item);
	
	/**
	 * ��ҳ��ѯ
	 * @param queryString
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageModel findAllItem( String queryString,
			int pageNo, int pageSize);
	
	/**
	 * �޸�����
	 * @param item
	 */
	public void modifyItem(Item item);
	
	/**
	 * �������ϴ����ѯ
	 * @param itemNo
	 * @return
	 */
	public Item findItemById(String itemNo);
	
	/**
	 * �����ϴ��ļ�
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(String itemNo, String uploadFileName);
}
