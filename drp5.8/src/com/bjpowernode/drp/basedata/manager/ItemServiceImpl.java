package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.dao.ItemDao;
import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.PageModel;

/**
 * ���õ���ģʽʵ��
 * @author Administrator
 *
 */
public class ItemServiceImpl implements ItemService {

	public void addItem(Item item) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (getItemDao().findItemById(conn, item.getItemNo()) != null) {
				throw new AppException("���ϴ����ظ�������=[" + item.getItemNo() + "]��");
			}			
			getItemDao().addItem(conn, item);
		}catch(AppException e) {
			throw e; //������ظ��쳣��������ʾ�׳������������׵�servlet��,Ҳ���Բ�������
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * ��ҳ��ѯ
	 * @param queryString
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	@SuppressWarnings("unchecked")
	public PageModel findAllItem( String queryString,
			int pageNo, int pageSize) {
		Connection conn = null;
		PageModel pageModel = null;
		try {
			conn = DBUtil.getConnection();
			pageModel = getItemDao().findAllItem(conn, queryString, pageNo, pageSize);
		}finally {
			DBUtil.close(conn);
		}
		return pageModel;
	}
	
	/**
	 * �޸�����
	 * @param item
	 */
	public void modifyItem(Item item) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			getItemDao().modifyItem(conn, item);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * �������ϴ����ѯ
	 * @param itemNo
	 * @return
	 */
	public Item findItemById(String itemNo) {
		Connection conn = null;
		Item item = null;
		try {
			conn = DBUtil.getConnection();
			item = getItemDao().findItemById(conn, itemNo);
		}finally {
			DBUtil.close(conn);
		}
		return item;
	}
	
	/**
	 * �����ϴ��ļ�
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(String itemNo, String uploadFileName) {	
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			getItemDao().modifyUploadFileNameField(conn, itemNo, uploadFileName);
		}finally {
			DBUtil.close(conn);
		}
	}
	
	private ItemDao getItemDao() {
		ItemDao itemDao = (ItemDao)BeanFactory.getInstance().getBean(ItemDao.class);
		return itemDao;
	}
}
