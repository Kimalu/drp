package com.bjpowernode.drp.basedata.manager;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.dao.ItemDao;
import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.AppException;
import com.bjpowernode.drp.util.BeanFactory;
import com.bjpowernode.drp.util.DBUtil;
import com.bjpowernode.drp.util.PageModel;

/**
 * 采用单例模式实现
 * @author Administrator
 *
 */
public class ItemServiceImpl implements ItemService {

	public void addItem(Item item) {
		Connection conn = null;
		try {
			conn = DBUtil.getConnection();
			if (getItemDao().findItemById(conn, item.getItemNo()) != null) {
				throw new AppException("物料代码重复，代码=[" + item.getItemNo() + "]！");
			}			
			getItemDao().addItem(conn, item);
		}catch(AppException e) {
			throw e; //如果拦截该异常，必须显示抛出，这样才能抛到servlet中,也可以不用拦截
		}finally {
			DBUtil.close(conn);
		}
	}
	
	/**
	 * 分页查询
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
	 * 修改物料
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
	 * 根据物料代码查询
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
	 * 保存上传文件
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
