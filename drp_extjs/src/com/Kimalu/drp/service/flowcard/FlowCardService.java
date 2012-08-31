package com.Kimalu.drp.service.flowcard;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.Kimalu.drp.dao.Page;
import com.Kimalu.drp.dao.flowcard.FlowCardDao;
import com.Kimalu.drp.dao.flowcard.FlowCardDetailDao;
import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.FiscalYearPeriod;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.User;
import com.Kimalu.drp.domain.flowcard.FlowCard;
import com.Kimalu.drp.domain.flowcard.FlowCardDetail;
import com.Kimalu.drp.service.FiscalYearPeriodService;
import com.Kimalu.drp.util.FlowCardNoGen;

@Service
public class FlowCardService {
	@Autowired
	private FlowCardDao flowCardDao;
	@Autowired
	private FlowCardDetailDao flowCardDetailDao;
	@Autowired
	private FiscalYearPeriodService fYPService;
	@Autowired
	private FlowCardNoGen flowCardNoGen;
	@Transactional
	public void addFlowCard(User recorder,Client c,List<Item> itemList, List<Region> aimClientList,List<Double> qtyList) {
		FlowCard fc=new FlowCard();
		fc.setFlowCardNo(flowCardNoGen.getFlowCardNo());
		fc.setRecorder(recorder);
		fc.setClient(c);
		fc.setVouSts(FlowCard.RECORDING);
		FiscalYearPeriod fyp=fYPService.getCurrentFYP();
		fc.setFiscalYearPeriod(fyp);
		fc.setOptDate(new Date());
		flowCardDao.save(fc);
		
		for(int i=0;i<itemList.size();i++){
			FlowCardDetail fcd=new FlowCardDetail();
			fcd.setAdjustFlag(FlowCardDetail.NO_ADJUST);
			fcd.setOptQty(qtyList.get(i));
			fcd.setFlowCard(fc);
			fcd.setItem(itemList.get(i));
			fcd.setTemiClient(aimClientList.get(i));
			flowCardDetailDao.save(fcd);
		}
	}
	
	@Transactional
	public String getLastFlowCardNo(String cDate) {
		String hql="select max(f.flowCardNo) as lastFCNo from FlowCard f where f.flowCardNo like :cdate";
		Map<String,Object> paramMap=new HashMap<String,Object>();
		paramMap.put("cdate", cDate+"%");
		String lastFCNo=(String)flowCardDao.createQuery(hql, paramMap).uniqueResult();
		return lastFCNo;
	}
	@Transactional
	public Page<FlowCard> getFlowCardList(Page<FlowCard> page) {
		flowCardDao.getFlowCardList(page);
		flowCardDao.sessionClear();
		for(int i=0;i<page.getList().size();i++){
			page.getList().get(i).getClient().setChlidClients(null);
			page.getList().get(i).getClient().setParentClient(null);
		}
		return page;
	}
	@Transactional
	public List<FlowCardDetail> getFlowCardDetailList(String flowCardId){
		return flowCardDetailDao.getFlowCardDetailList(flowCardId);
	}
	@Transactional
	public void delFlowCardDetailList(String flowCardId){
		flowCardDetailDao.delFlowCardDetailByFlowCardId(flowCardId);
	}

	@Transactional
	public void modifyFlowCard(String fcId,Client c, List<Item> itemList, List<Region> aimClientList, List<Double> qtyList) {
		FlowCard fc=flowCardDao.getEntityById(fcId);
		fc.setClient(c);
		flowCardDao.update(fc);
		this.delFlowCardDetailList(fcId);
		for(int i=0;i<itemList.size();i++){
			FlowCardDetail fcd=new FlowCardDetail();
			fcd.setAdjustFlag(FlowCardDetail.NO_ADJUST);
			fcd.setOptQty(qtyList.get(i));
			fcd.setFlowCard(fc);
			fcd.setItem(itemList.get(i));
			fcd.setTemiClient(aimClientList.get(i));
			flowCardDetailDao.save(fcd);
		}
	}
	@Transactional
	public void updateFlowCard(FlowCard fc){
		flowCardDao.update(fc);
	}
	
	
	public FlowCard getFlowCardById(String fcid) {
		return flowCardDao.getEntityById(fcid);
	}
}
