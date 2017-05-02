package com.dreamer.domain.user;

public enum LogistFeeTransferType {
	NORMAL("主动转出"),PAY("充值"),ERROR("失败/未付款");
    public String desc;

    LogistFeeTransferType(String desc){
        this.desc=desc;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}