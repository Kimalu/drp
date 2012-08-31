package com.Kimalu.drp.domain;

import java.util.List;


/**
 * 流向单明细信息
 * @author Administrator
 *
 */
public class FlowCardDetail {

	 //标识
	 private int flowCardDetailId;
	 
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
	 
	 //需方客户
	 private AimClient AimClient;
	 
	 //物料信息
	 private Item item;
	 
	 //流向单主信息
	 private FlowCard flowCard;

	public int getFlowCardDetailId() {
		return flowCardDetailId;
	}

	public void setFlowCardDetailId(int flowCardDetailId) {
		this.flowCardDetailId = flowCardDetailId;
	}

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

	public AimClient getAimClient() {
		return AimClient;
	}

	public void setAimClient(AimClient aimClient) {
		AimClient = aimClient;
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
}
