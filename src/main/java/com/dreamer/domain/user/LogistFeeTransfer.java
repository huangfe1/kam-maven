package com.dreamer.domain.user;

import ps.mx.otter.utils.date.DateUtil;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.Date;
import java.util.Objects;

public class LogistFeeTransfer implements java.io.Serializable {

	private static final long serialVersionUID = 2759558931721988426L;
	// Fields

	private Integer id;
	private Integer version;
	private User userByToAgent;
	private User userByFromAgent;
	private Date transferTime;
	private Date updateTime;
	private String remark;
	private Double fee;
	private LogistFeeTransferType type;
    private String out_trade_no;//订单号码

	// Constructors


	public void transferfee(Agent from,Agent to,Double fee){
		userByToAgent=to;
		userByFromAgent=from;
		transferTime=new Date();
		this.fee=fee;
		type=LogistFeeTransferType.NORMAL;
		from.transferLogistFee(to,fee);//转移置换券
	}

	/** default constructor */
	public LogistFeeTransfer() {
	}

	/** minimal constructor */
	public LogistFeeTransfer(User userByToAgent, User userByFromAgent, Double fee) {
		this.userByToAgent = userByToAgent;
		this.userByFromAgent = userByFromAgent;
		this.fee = fee;
	}

	/** full constructor */
	public LogistFeeTransfer(User userByToAgent, User userByFromAgent,
                             Date transferTime, Date updateTime, String remark,
                             Double fee) {
		this.userByToAgent = userByToAgent;
		this.userByFromAgent = userByFromAgent;
		this.transferTime = transferTime;
		this.updateTime = updateTime;
		this.remark = remark;
		this.fee = fee;
	}

    /**
     * 在线充值  提交物流费订单
     * @param userByFromAgent
     * @param userByToAgent
     */
    public void commit(User userByFromAgent,User userByToAgent){
        setUpdateTime(new Timestamp(System.currentTimeMillis()));//设置提交时间
        setUserByFromAgent(userByFromAgent);//假定为公司
        setUserByToAgent(userByToAgent);//收置换券的人
		setType(LogistFeeTransferType.ERROR);//没有付款
		setOut_trade_no(fillOrderNo());//设置订单编号
    }

//    /**
//     * 全部用代金券支付
//     * @param time
//     */
//	public void payForfeeByVoucher(String time){
//        setTransferTime(DateUtil.string2date(time, "yyyyMMddHHmmss"));
//        setType(feeTransferType.PAY);
//        setRemark(feeTransferType.PAY.desc+"使用了代金券"+getUseVoucher());
//        getUserByToAgent().getAccounts().deductVoucher(getUseVoucher(),"充值置换券扣减,订单"+getOut_trade_no());
//        getUserByToAgent().getAccounts().increasefee(getfee(),"在线充值,订单"+getOut_trade_no()+"使用代金券"+getUseVoucher());
//	}

    public void payForfee(String time){
        setTransferTime(DateUtil.string2date(time, "yyyyMMddHHmmss"));
        setType(LogistFeeTransferType.PAY);
        setRemark(VoucherTransferType.PAY.desc);
//        if(getUseVoucher()>0){//有代金券可用
//            getUserByToAgent().getAccounts().deductVoucher(getUseVoucher(),"充值置换券,订单"+getOut_trade_no());
//        }
        getUserByToAgent().getAccounts().increaseLogistFee(getfee(),"在线充值,订单"+getOut_trade_no());
    }

    public String fillOrderNo(){
//        orderNo=DateUtil.date2string(Date.from(Instant.now()), DateUtil.FULL_FORMAT);
//        return Integer.toHexString(Objects.hashCode(DateUtil.date2string(Date.from(Instant.now()),"MMddHHmmss")));
        return Integer.toHexString(Objects.hashCode(userByToAgent.getId()))+DateUtil.date2string(Date.from(Instant.now()), "MMddHHmmss");
    }
	// Property accessors

	public Integer getId() {
		return this.id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public Integer getVersion() {
		return this.version;
	}

	public Double getFee() {
		return fee;
	}

	public void setFee(Double fee) {
		this.fee = fee;
	}

	public LogistFeeTransferType getType() {
		return type;
	}

	public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public void setVersion(Integer version) {
		this.version = version;
	}

	public User getUserByToAgent() {
		return this.userByToAgent;
	}

	public void setUserByToAgent(User userByToAgent) {
		this.userByToAgent = userByToAgent;
	}

	public User getUserByFromAgent() {
		return this.userByFromAgent;
	}

	public void setUserByFromAgent(User userByFromAgent) {
		this.userByFromAgent = userByFromAgent;
	}

	public Date getTransferTime() {
		return this.transferTime;
	}

	public void setTransferTime(Date transferTime) {
		this.transferTime = transferTime;
	}

	public Date getUpdateTime() {
		return this.updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRemark() {
		return this.remark;
	}



	public void setRemark(String remark) {
		this.remark = remark;
	}

	public Double getfee() {
		return fee;
	}

	public void setType(LogistFeeTransferType type) {
		this.type = type;
	}

	public void setfee(Double fee) {
		this.fee = fee;
	}
}