package com.Kimalu.drp.domain.flowcard;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.Item;
import com.Kimalu.drp.domain.Region;
import com.Kimalu.drp.domain.TemiClient;

/**
 * 流向单明细信息
 * @author Administrator
 *
 */
@Entity
@Component
@Table(name="t_flow_card_detail")
public class FlowCardDetail {
	
	public static final String NO_ADJUST="N";
	public static final String ADJUSTED="Y";
	 //标识
	@Id
	@GeneratedValue(generator="myuuid")
	@GenericGenerator(name="myuuid",strategy="uuid")
	 private String flowCardDetailId;
	 
	 //操作数量
	 private double optQty;
	 
	 //调整数据量
	 private double adjustQty;
	 
	 //调整原因
	 private String adjustReason;
	 
	 //调整标记
	 //Y：调整
	 //N：未调整	 
	 private  String adjustFlag;
	 
	 //物料信息
	 @ManyToOne
	 private Item item;
	 @ManyToOne
	 private Region temiClient;
	 
	 //流向单主信息
	 @ManyToOne
	 private FlowCard flowCard;


	public double getOptQty() {
		return optQty;
	}

	public void setOptQty(double optQty) {
		this.optQty = optQty;
	}

	public double getAdjustQty() {
		return adjustQty;
	}

	public void setAdjustQty(double adjustQty) {
		this.adjustQty = adjustQty;
	}

	public String getAdjustReason() {
		return adjustReason;
	}

	public void setAdjustReason(String adjustReason) {
		this.adjustReason = adjustReason;
	}

	public String getAdjustFlag() {
		return adjustFlag;
	}

	public void setAdjustFlag(String adjustFlag) {
		this.adjustFlag = adjustFlag;
	}

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public FlowCard getFlowCard() {
		return flowCard;
	}

	public void setFlowCard(FlowCard flowCard) {
		this.flowCard = flowCard;
	}

	public Region getTemiClient() {
		return temiClient;
	}

	public void setTemiClient(Region temiClient) {
		this.temiClient = temiClient;
	}

	public String getFlowCardDetailId() {
		return flowCardDetailId;
	}

	public void setFlowCardDetailId(String flowCardDetailId) {
		this.flowCardDetailId = flowCardDetailId;
	}

}
