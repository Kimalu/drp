package com.Kimalu.drp.service;

import static org.junit.Assert.*;

import java.util.List;

import org.junit.Test;

import com.Kimalu.drp.domain.AbstractDataDict;

public class ItemServiceTest {

	@Test
	public void testGetDataDictList() {
		ItemService itemService=ItemService.getInstance();
		List<AbstractDataDict> ddlist=itemService.getDataDictList("itemUnit");
		System.out.println(ddlist);
	}

}
