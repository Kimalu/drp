package com.bjpowernode.drp.util;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.sql.Connection;

/**
 * ���ö�̬����������򵥲��ֵ�����
 * @author Administrator
 *
 */
public class TransactionHandler implements InvocationHandler {

	private Object targetObject;
	
	public Object newProxyObject(Object targetObject) {
		this.targetObject = targetObject;
		return Proxy.newProxyInstance(
				targetObject.getClass().getClassLoader(), 
				targetObject.getClass().getInterfaces(), 
				this);
	}
	
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		Connection conn = null;
		Object ret = null;
		try {
			conn = ConnectionManager.getConnection();
			if (method.getName().startsWith("add") || 
				method.getName().startsWith("modify") || 
				method.getName().startsWith("del")) {
				System.out.println("--------begin transaction----------");
				//��������
				ConnectionManager.setAutoCommit(conn, false);
			}
			//����ҵ���߼�����
			ret = method.invoke(this.targetObject, args);
			if (!conn.getAutoCommit()) {
				System.out.println("--------commit transaction----------");
				//�ύ����
				ConnectionManager.commit(conn);
			}
		}catch(Exception e) {
			//�ع�����
			ConnectionManager.rollback(conn);
			if (e instanceof InvocationTargetException) {
				InvocationTargetException te = (InvocationTargetException)e;
				throw te.getTargetException();
			}
			throw new AppException("����ʧ�ܣ�");
		}finally {
			ConnectionManager.closeConnection();
		}
		return ret;
	}

}
