package com.Kimalu.drp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.domain.ClientLevel;
import com.Kimalu.drp.domain.ItemCategory;
import com.Kimalu.drp.domain.ItemUnit;
import com.Kimalu.drp.domain.TemiClientType;
import com.Kimalu.drp.service.DataDictService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class DataDictServiceTest {
	@Autowired
	private DataDictService ddService;

	@Test
	public void testAdddatadict(){
		ItemCategory ic1=new ItemCategory();
		ic1.setName("ҽ����е");
		ItemCategory ic2=new ItemCategory();
		ic2.setName("�г�ҩ");
		ItemCategory ic3=new ItemCategory();
		ic3.setName("��ҩ");
		ddService.saveDataDict(ic1);
		ddService.saveDataDict(ic2);
		ddService.saveDataDict(ic3);
		
	}
	
	@Test
	public void testAdddatadict2(){
		ItemUnit iu1=new ItemUnit();
		iu1.setName("��");
		ItemUnit iu2=new ItemUnit();
		iu2.setName("Ƭ");
		ItemUnit iu3=new ItemUnit();
		iu3.setName("��");
		ddService.saveDataDict(iu1);
		ddService.saveDataDict(iu2);
		ddService.saveDataDict(iu3);
		
	}
	
	@Test
	public void testAdddatadict3(){
		ClientLevel cl1=new ClientLevel();
		cl1.setName("һ��������");
		ClientLevel cl2=new ClientLevel();
		cl2.setName("����������");
		ClientLevel cl3=new ClientLevel();
		cl3.setName("����������");
		ClientLevel cl4=new ClientLevel();
		cl4.setName("�ܲ�");
		ddService.saveDataDict(cl1);
		ddService.saveDataDict(cl2);
		ddService.saveDataDict(cl3);
		ddService.saveDataDict(cl4);
		
	}
	
	@Test
	public void testAdddatadict4(){
		TemiClientType cl1=new TemiClientType();
		cl1.setName("�׼�ҽԺ");
		TemiClientType cl2=new TemiClientType();
		cl2.setName("�Ҽ�ҽԺ");
		TemiClientType cl3=new TemiClientType();
		cl3.setName("����ҽԺ");
		TemiClientType cl4=new TemiClientType();
		cl4.setName("ҩ��");
		TemiClientType cl5=new TemiClientType();
		cl5.setName("����");
		ddService.saveDataDict(cl1);
		ddService.saveDataDict(cl2);
		ddService.saveDataDict(cl3);
		ddService.saveDataDict(cl4);
		ddService.saveDataDict(cl5);
	}
	
}
