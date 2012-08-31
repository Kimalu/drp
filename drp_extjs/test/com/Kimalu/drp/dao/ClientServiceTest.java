package com.Kimalu.drp.dao;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.service.DataDictService;
import com.Kimalu.drp.service.basedata.RegionService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:applicationContext-test.xml")
@TransactionConfiguration(transactionManager="transactionManager", defaultRollback=false)
@Transactional
public class ClientServiceTest {
	
	@Autowired
	private RegionService regionService;
	@Autowired
	private DataDictService ddService;

	@Test
	public void testAddClient(){
		Region c=new Region();
		c.setName("所有分销商");
		c.setIsClient("N");
		regionService.addRegion(c);
	}
//	@Test
//	public void testAddClient(){
//		ClientLevel cl1=new ClientLevel();
//		cl1.setId(1);
//		cl1.setName("cl1");
//		ClientLevel cl2=new ClientLevel();
//		cl2.setId(2);
//		cl2.setName("cl2");
//		Client c1=new Client();
//		c1.setName("a");
//		c1.setParentClient(null);
//		c1.setClientLevel(cl1);
//		Client c2=new Client();
//		c2.setName("b");
//		c2.setParentClient(c1);
//		c2.setClientLevel(cl2);
//		Client c3=new Client();
//		c3.setName("c");
//		c3.setParentClient(c1);
//		c3.setClientLevel(cl1);
//		Set<Client> cs=new HashSet<Client>();
//		cs.add(c2);
//		cs.add(c3);
//		c1.setChlidClients(cs);
//		clientService.addClient(c1);
//	}
//	@Test
//	public void testAddTemiClient(){
//		TemiClientType ct1=new TemiClientType();
//		ct1.setId(22);
//		ct1.setName("tct1");
//		TemiClientType ct2=new TemiClientType();
//		ct2.setId(23);
//		ct2.setName("tct2");
//		TemiClient c1=new TemiClient();
//		c1.setName("a");
//		c1.setParentTemiClient(null);
//		c1.setTemiClientType(ct1);
//		TemiClient c2=new TemiClient();
//		c2.setName("b");
//		c2.setParentTemiClient(c1);
//		c2.setTemiClientType(ct2);
//		TemiClient c3=new TemiClient();
//		c3.setName("c");
//		c3.setParentTemiClient(c1);
//		c3.setTemiClientType(ct1);
//		Set<TemiClient> cs=new HashSet<TemiClient>();
//		cs.add(c2);
//		cs.add(c3);
//		c1.setChlidTemiClients(cs);
//		temiClientService.addTemiClient(c1);
//		//clientService.addClient(c1);
//	}
//	@Test
//	public void testAddTemiClientType(){
//		TemiClientType ct2=new TemiClientType();
//		ct2.setId(4);
//		ct2.setName("tct2");
//		dataDictDao.save(ct2);
//		//temiClientService.addTemiClient(c1);
//		//clientService.addClient(c1);
//	}
	
//	@Test
//	public void testGetAimClientType(){
//		AimClient ac=aimClientService.queryAimClientbyId(45);
//		System.out.println(ac.getCategoryName());
//		System.out.println(ac.getName());
//	}
	
	
}
