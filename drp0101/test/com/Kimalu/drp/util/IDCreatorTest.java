package com.Kimalu.drp.util;

import org.junit.Test;


public class IDCreatorTest {
	@Test
	public void testGetID(){
		System.out.println(IDCreator.getInstance().getID("t_fiscal_year_period"));
	}

}
