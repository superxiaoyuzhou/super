package com.example.excel.entity;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 分润明细表
 * @author kris
 */
public class PromoteFeeSplit {
    private String id;

    private String userNo;

    private String promoteCode;

    private String parentPromoteCode;

    private String prodCode;
    private String tranMethod;

    private BigDecimal tranAmount;

    private Date tranDate;

    private BigDecimal feeSplitIn;

    private BigDecimal feeSplitOut;

    private BigDecimal rateIn;

    private BigDecimal rateOut;

    private String orderNoIn;

    private String orderNoOut;

    private Date createTime;

    private Date updateTime;

    /**
     * @return ID
     */
    public String getId() {
        return id;
    }

    /**
     * @param id
     */
    public void setId(String id) {
        this.id = id;
    }

    /**
     * @return USER_NO
     */
    public String getUserNo() {
        return userNo;
    }

    /**
     * @param userNo
     */
    public void setUserNo(String userNo) {
        this.userNo = userNo;
    }

    /**
     * @return PROMOTE_CODE
     */
    public String getPromoteCode() {
        return promoteCode;
    }

    /**
     * @param promoteCode
     */
    public void setPromoteCode(String promoteCode) {
        this.promoteCode = promoteCode;
    }

    /**
     * @return PARENT_PROMOTE_CODE
     */
    public String getParentPromoteCode() {
        return parentPromoteCode;
    }

    /**
     * @param parentPromoteCode
     */
    public void setParentPromoteCode(String parentPromoteCode) {
        this.parentPromoteCode = parentPromoteCode;
    }

    /**
     * @return PROD_CODE
     */
    public String getProdCode() {
        return prodCode;
    }

    /**
     * @param prodCode
     */
    public void setProdCode(String prodCode) {
        this.prodCode = prodCode;
    }

    /**
     * @return TRAN_METHOD
     */
    public String getTranMethod() {
        return tranMethod;
    }

    /**
     * @param tranMethod
     */
    public void setTranMethod(String tranMethod) {
        this.tranMethod = tranMethod;
    }

    /**
     * @return TRAN_AMOUNT
     */
    public BigDecimal getTranAmount() {
        return tranAmount;
    }

    /**
     * @param tranAmount
     */
    public void setTranAmount(BigDecimal tranAmount) {
        this.tranAmount = tranAmount;
    }

    /**
     * @return TRAN_DATE
     */
    public Date getTranDate() {
        return tranDate;
    }

    /**
     * @param tranDate
     */
    public void setTranDate(Date tranDate) {
        this.tranDate = tranDate;
    }

    /**
     * @return FEE_SPLIT_IN
     */
    public BigDecimal getFeeSplitIn() {
        return feeSplitIn;
    }

    /**
     * @param feeSplitIn
     */
    public void setFeeSplitIn(BigDecimal feeSplitIn) {
        this.feeSplitIn = feeSplitIn;
    }

    /**
     * @return FEE_SPLIT_OUT
     */
    public BigDecimal getFeeSplitOut() {
        return feeSplitOut;
    }

    /**
     * @param feeSplitOut
     */
    public void setFeeSplitOut(BigDecimal feeSplitOut) {
        this.feeSplitOut = feeSplitOut;
    }

    /**
     * @return RATE_IN
     */
    public BigDecimal getRateIn() {
        return rateIn;
    }

    /**
     * @param rateIn
     */
    public void setRateIn(BigDecimal rateIn) {
        this.rateIn = rateIn;
    }

    /**
     * @return RATE_OUT
     */
    public BigDecimal getRateOut() {
        return rateOut;
    }

    /**
     * @param rateOut
     */
    public void setRateOut(BigDecimal rateOut) {
        this.rateOut = rateOut;
    }

    /**
     * @return ORDER_NO_IN
     */
    public String getOrderNoIn() {
        return orderNoIn;
    }

    /**
     * @param orderNoIn
     */
    public void setOrderNoIn(String orderNoIn) {
        this.orderNoIn = orderNoIn;
    }

    /**
     * @return ORDER_NO_OUT
     */
    public String getOrderNoOut() {
        return orderNoOut;
    }

    /**
     * @param orderNoOut
     */
    public void setOrderNoOut(String orderNoOut) {
        this.orderNoOut = orderNoOut;
    }

    /**
     * @return CREATE_TIME
     */
    public Date getCreateTime() {
        return createTime;
    }

    /**
     * @param createTime
     */
    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    /**
     * @return UPDATE_TIME
     */
    public Date getUpdateTime() {
        return updateTime;
    }

    /**
     * @param updateTime
     */
    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", id=").append(id);
        sb.append(", userNo=").append(userNo);
        sb.append(", promoteCode=").append(promoteCode);
        sb.append(", parentPromoteCode=").append(parentPromoteCode);
        sb.append(", prodCode=").append(prodCode);
        sb.append(", tranMethod=").append(tranMethod);
        sb.append(", tranAmount=").append(tranAmount);
        sb.append(", tranDate=").append(tranDate);
        sb.append(", feeSplitIn=").append(feeSplitIn);
        sb.append(", feeSplitOut=").append(feeSplitOut);
        sb.append(", rateIn=").append(rateIn);
        sb.append(", rateOut=").append(rateOut);
        sb.append(", orderNoIn=").append(orderNoIn);
        sb.append(", orderNoOut=").append(orderNoOut);
        sb.append(", createTime=").append(createTime);
        sb.append(", updateTime=").append(updateTime);
        sb.append("]");
        return sb.toString();
    }
}