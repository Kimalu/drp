package com.bjpowernode.drp.util;

import java.util.HashMap;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

/**
 * Bean����
 * @author Administrator
 *
 */
public class BeanFactory {

	private static BeanFactory instance = new BeanFactory();
	
	//��Ų�Ʒ{key=��Ʒ��ţ�value=�����Ʒʵ��}
	private Map beans = new HashMap();
	
	private Document doc;
	
	private BeanFactory() {
		try {
			doc = new SAXReader().read(Thread.currentThread().getContextClassLoader().getResourceAsStream("applicationContext.xml"));
		} catch (DocumentException e) {
			throw new AppException("ϵͳ��������ϵϵͳ����Ա");
		}
	}
	
	public static BeanFactory getInstance() {
		return instance;
	}
	
//	/**
//	 * ���ݲ�Ʒ��ʶ�õ���Ʒ
//	 * @param id
//	 * @return
//	 */
//	public synchronized Object  getBean(String id) {
//		//�����Map�����Ѿ������Ĳ�Ʒ����
//		if (beans.containsKey(id)) {
//			return beans.get(id);
//		}
//		//����xml�ļ���id���Ե���ĳֵ��bean��ǩ
//		Element elt = (Element)doc.selectObject("//bean[@id=\"" + id + "\"]");
//		Object object = null;
//		try {
//			object = Class.forName(elt.attributeValue("class")).newInstance();
//			//�������õĲ�Ʒ���뵽Map��
//			beans.put(id, object);
//		} catch (Exception e) {
//			e.printStackTrace();
//			throw new AppException("ϵͳ��������ϵϵͳ����Ա");
//		}
//		return object;
//	}
	
//	/**
//	 * ���ݲ�Ʒ��ʶ�õ���Ʒ
//	 * @param id
//	 * @return
//	 */
//	public  Object  getBean(String id) {
//		synchronized(this) {
//			//�����Map�����Ѿ������Ĳ�Ʒ����
//			if (beans.containsKey(id)) {
//				return beans.get(id);
//			}
//			//����xml�ļ���id���Ե���ĳֵ��bean��ǩ
//			Element elt = (Element)doc.selectObject("//bean[@id=\"" + id + "\"]");
//			Object object = null;
//			try {
//				object = Class.forName(elt.attributeValue("class")).newInstance();
//				//�������õĲ�Ʒ���뵽Map��
//				beans.put(id, object);
//			} catch (Exception e) {
//				e.printStackTrace();
//				throw new AppException("ϵͳ��������ϵϵͳ����Ա");
//			}
//			return object;
//		}
//	}

	/**
	 * ���ݲ�Ʒ��ʶ�õ���Ʒ
	 * @param id
	 * @return
	 */
	public  Object  getBean(Class c) {
		synchronized(beans) {
			//�����Map�����Ѿ������Ĳ�Ʒ����
			if (beans.containsKey(c.getName())) {
				return beans.get(c.getName());
			}
			//����xml�ļ���id���Ե���ĳֵ��bean��ǩ
			
			Object object = null;
			try {
				Element elt = (Element)doc.selectObject("//bean[@id=\"" + c.getName() + "\"]");
				object = Class.forName(elt.attributeValue("class")).newInstance();
				//�������õĲ�Ʒ���뵽Map��
				beans.put(c.getName(), object);
			} catch (Exception e) {
				e.printStackTrace();
				throw new AppException("ϵͳ��������ϵϵͳ����Ա");
			}
			return object;
		}
	}
}
