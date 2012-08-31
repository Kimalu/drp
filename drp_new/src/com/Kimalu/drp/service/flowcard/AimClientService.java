package com.Kimalu.drp.service.flowcard;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.flowcard.AimClientDao;
import com.Kimalu.drp.domain.Region;

@Service
public class AimClientService {
	@Autowired
	private AimClientDao aimClientDao;
	
	public Region getAimClientbyId(String id){
		return aimClientDao.getEntityById(id);
	}
	
	@Transactional
	public Page<Region> getAimClientList(Page<Region> page){
		return aimClientDao.getAimClientList(page);
	}
	@Transactional
	public Page<Region> queryAimClientListByName(Page<Region> page,String queryAimClientName){
		return aimClientDao.queryAimClientListByName(page,queryAimClientName);
	}
}
