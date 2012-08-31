package com.Kimalu.drp.domain.flowcard;


import java.util.Date;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.GenericGenerator;
import org.springframework.stereotype.Component;

import com.Kimalu.drp.domain.Client;
import com.Kimalu.drp.domain.FiscalYearPeriod;
import com.Kimalu.drp.domain.User;


/**
 * ��������Ϣ
 * @author Administrator
 *
 */
@Entity
@Component
@Table(name="t_flow_card_master")
public class FlowCard {
	
	public static final String SUBMIT_CHECKING="S";
	public static final String RECORDING="N";
	@Id
	@GeneratedValue(generator="myuuid")
	@GenericGenerator(name="myuuid",strategy="uuid")
	private String fcId;
	
	//���򵥺�,yyyymmdd0001
	private String  flowCardNo;
	
	//��������
	//D������
	//A���������̵㣩
	private String optType;
	
	//����ʱ��
	private Date optDate;
	
	//����״̬
	//S������
	//N��¼��
	private String vouSts;
	
	//��������
	private Date adjustDate;
	
	//�������
	private Date spotDate;
	
	//�������
	private String spotDesc;
	
	//��������
	private Date confDate;
	
	//¼����
	@ManyToOne
	private User recorder;
	
	//������
	@ManyToOne
	private User adjuster;
	
	//�����
	@ManyToOne
	private User spotter;
	
	//������
	@ManyToOne
	private User confirmer;
	
	//����������
	@ManyToOne
	private Client client;
	
	//��ƺ�����
	@ManyToOne
	private FiscalYearPeriod fiscalYearPeriod;

	
	public String getFlowCardNo() {
		return flowCardNo;
	}

	public void setFlowCardNo(String flowCardNo) {
		this.flowCardNo = flowCardNo;
	}

	public String getOptType() {
		return optType;
	}

	public void setOptType(String optType) {
		this.optType = optType;
	}

	public Date getOptDate() {
		return optDate;
	}

	public void setOptDate(Date optDate) {
		this.optDate = optDate;
	}

	public String getVouSts() {
		return vouSts;
	}

	public void setVouSts(String vouSts) {
		this.vouSts = vouSts;
	}

	public Date getAdjustDate() {
		return adjustDate;
	}

	public void setAdjustDate(Date adjustDate) {
		this.adjustDate = adjustDate;
	}

	public Date getSpotDate() {
		return spotDate;
	}

	public void setSpotDate(Date spotDate) {
		this.spotDate = spotDate;
	}

	public String getSpotDesc() {
		return spotDesc;
	}

	public void setSpotDesc(String spotDesc) {
		this.spotDesc = spotDesc;
	}

	public Date getConfDate() {
		return confDate;
	}

	public void setConfDate(Date confDate) {
		this.confDate = confDate;
	}

	public User getRecorder() {
		return recorder;
	}

	public void setRecorder(User recorder) {
		this.recorder = recorder;
	}

	public User getAdjuster() {
		return adjuster;
	}

	public void setAdjuster(User adjuster) {
		this.adjuster = adjuster;
	}

	public User getSpotter() {
		return spotter;
	}

	public void setSpotter(User spotter) {
		this.spotter = spotter;
	}

	public User getConfirmer() {
		return confirmer;
	}

	public void setConfirmer(User confirmer) {
		this.confirmer = confirmer;
	}

	public Client getClient() {
		return client;
	}

	public void setClient(Client client) {
		this.client = client;
	}

	public FiscalYearPeriod getFiscalYearPeriod() {
		return fiscalYearPeriod;
	}

	public void setFiscalYearPeriod(FiscalYearPeriod fiscalYearPeriod) {
		this.fiscalYearPeriod = fiscalYearPeriod;
	}

	public String getFcId() {
		return fcId;
	}

	public void setFcId(String fcId) {
		this.fcId = fcId;
	}
	
	
}
