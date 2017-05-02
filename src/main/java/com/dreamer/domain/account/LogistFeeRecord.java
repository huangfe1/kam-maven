package com.dreamer.domain.account;

import com.dreamer.domain.user.Agent;

import java.util.Date;

public class LogistFeeRecord implements java.io.Serializable{

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	private Integer id;

	private Integer type;//记录类型

	private String more;//备注

	private Agent agent;//谁变动

	private Double fee;//物流费

	private Date updateTime;//更新时间

	private Integer version;//数据版本

	private Double fee_now;//变更后的物流费

	private Integer hasNoticed;//0需要通知  1已经通知

	public LogistFeeRecord(){

	}


    public LogistFeeRecord(Integer type,Agent agent,Double advance,String more,Double advance_now,Date updateTime){
        this.type=type;
        this.agent=agent;
        this.fee=advance;
        this.more=more;
        this.fee_now=advance_now;
        this.updateTime=updateTime;
    }

	public Integer getHasNoticed() {
		return hasNoticed;
	}

	public void setHasNoticed(Integer hasNoticed) {
		this.hasNoticed = hasNoticed;
	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public Double getFee_now() {
		return fee_now;
	}

	public void setFee_now(Double fee_now) {
		this.fee_now = fee_now;
	}

	public Integer getType() {
		return type;
	}

	public void setType(Integer type) {
		this.type = type;
	}

	public String getMore() {
		return more;
	}

	public void setMore(String more) {
		this.more = more;
	}



	public Agent getAgent() {
		return agent;
	}

	public void setAgent(Agent agent) {
		this.agent = agent;
	}

	public Double getfee() {
		return fee;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public void setfee(Double fee) {
		this.fee = fee;
	}

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public Double getfee_now() {
		return fee_now;
	}

	public void setfee_now(Double fee_now) {
		this.fee_now = fee_now;
	}
	
	
	
	
}
