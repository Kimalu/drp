package com.bjpowernode.drp.basedata.dao;

import java.sql.Connection;

import com.bjpowernode.drp.basedata.domain.Item;
import com.bjpowernode.drp.util.PageModel;

/**
 * ItemDao接口
 * @author Administrator
 *
 */
public interface ItemDao {

	/**
	 * 添加物料
	 * @param conn
	 * @param item
	 */
	public void addItem(Connection conn, Item item);
	
	/**
	 * 根据id查询
	 * @param conn
	 * @param itemNo
	 * @return 如果存在返回Item对象，否则返回null
	 */
	public Item findItemById(Connection conn, String itemNo);
	
	/**
	 * 分页查询
	 * @param conn
	 * @param queryString 查询条件（物料代码或名称）
	 * @param pageNo
	 * @param pageSize
	 * @return
	 */
	public PageModel findAllItem(Connection conn, String queryString, int pageNo, int pageSize);
	
	/**
	 * 修改物料
	 * @param conn
	 * @param item
	 */
	public void modifyItem(Connection conn, Item item);
	
	/**
	 * 保存上传的文件名称
	 * @param conn
	 * @param itemNo
	 * @param uploadFileName
	 */
	public void modifyUploadFileNameField(Connection conn, String itemNo, String uploadFileName);
}
