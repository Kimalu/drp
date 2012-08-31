package com.Kimalu.drp.dao;

import java.util.HashSet;
import java.util.Set;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.orm.hibernate3.HibernateTemplate;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.ClientLevel;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.ItemCategory;
import com.Kimalu.drp.domain.ItemUnit;
import com.Kimalu.drp.service.ClientService;
import com.Kimalu.drp.service.ItemService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class ItemServiceTest {
	@Autowired
	private HibernateTemplate ht;
	@Autowired
	private ItemService itemService;
	@Autowired
	private ClientService clientService;
	public ItemService getItemService() {
		return itemService;
	}

	public void setItemService(ItemService itemService) {
		this.itemService = itemService;
	}

	@Test
	public void testAddItem(){
		Item item=new Item();
		ItemCategory ic=new ItemCategory();
		ItemUnit iu=new ItemUnit();
		item.setItemNo("item20111120001");
		item.setItemName("ŒÔ¡œ1");
		item.setPattern("aaaapattern");
		item.setSpec("aaaspec");
		ic.setName("cate2");
		ht.save(ic);
		iu.setName("unit2");
		ht.save(iu);
		item.setItemCategory(ic);
		item.setItemUnit(iu);
		itemService.addItem(item);
	}
	@Test
	public void testAddClient(){
		ClientLevel cl1=new ClientLevel();
		cl1.setId(1);
		cl1.setName("cl1");
		ClientLevel cl2=new ClientLevel();
		cl2.setId(2);
		cl2.setName("cl2");
		Client c1=new Client();
		c1.setName("a");
		c1.setParentClient(null);
		c1.setClientLevel(cl1);
		Client c2=new Client();
		c2.setName("b");
		c2.setParentClient(c1);
		c2.setClientLevel(cl2);
		Client c3=new Client();
		c3.setName("c");
		c3.setParentClient(c1);
		c3.setClientLevel(cl1);
		Set<Client> cs=new HashSet<Client>();
		cs.add(c2);
		cs.add(c3);
		c1.setChlidClients(cs);
		clientService.addClient(c1);
	}
}
