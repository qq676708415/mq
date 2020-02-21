package com.hailiang.common.bean;

import com.alibaba.fastjson.JSON;

import java.math.BigDecimal;

public class InvestBean {
    /**
     * 交易流水号
     */
    public String seqNo;

    /**
     * 用户id
     */
    public String userId;

    public BigDecimal investAmount;

    public String getSeqNo() {
        return seqNo;
    }

    public void setSeqNo(String seqNo) {
        this.seqNo = seqNo;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public BigDecimal getInvestAmount() {
        return investAmount;
    }

    public void setInvestAmount(BigDecimal investAmount) {
        this.investAmount = investAmount;
    }

    public static void main(String[] args) {
        InvestBean bean = new InvestBean();
        bean.setSeqNo("123");
        bean.setInvestAmount(new BigDecimal("12000"));
        bean.setUserId("123456");

        System.out.println(JSON.toJSONString(bean));

        System.out.println(JSON.parseObject("value", String.class));
    }
}
