package com.Kimalu.drp.daoImpl;

import java.util.Date;

import org.junit.Test;


public class FlowCardDaoImplTest {
	@Test
	public void testGenVouNo(){
		FlowCardDaoImpl fcdi=new FlowCardDaoImpl();
		String no=fcdi.genVouNo();
		System.out.println(no);
//		System.out.println(new Date());
	}

}
