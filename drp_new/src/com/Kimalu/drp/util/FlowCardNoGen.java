package com.Kimalu.drp.util;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Kimalu.drp.service.flowcard.FlowCardService;

@Component
public class FlowCardNoGen {
	@Autowired
	private FlowCardService fcService;
	
	public String getFlowCardNo(){
		NumberFormat nf=new DecimalFormat("0000");
		String cDate=currentDate();
		String lastFCNo=fcService.getLastFlowCardNo(cDate);
		String newNo="";
		if(lastFCNo==null){
			newNo=cDate+"0001";
		}else{
			String No=lastFCNo.substring(7);
			int intNo=Integer.parseInt(No)+1;
			newNo= lastFCNo.substring(0,7)+nf.format(intNo).toString();
		}
		return newNo;
		
	}
	
	private String currentDate(){
		SimpleDateFormat sdf=new SimpleDateFormat("yyyyMMdd");
		Date date=new Date();
		return sdf.format(date);
	}

}
