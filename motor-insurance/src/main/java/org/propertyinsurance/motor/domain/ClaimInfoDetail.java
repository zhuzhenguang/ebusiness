package org.propertyinsurance.motor.domain;

import java.util.Date;

/**
 * 理赔详细信息
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:30
 */
public class ClaimInfoDetail {
    private String payCompany;           // 赔付保险公司
    private String claimNo;               // 立案号
    private String compensateNo;         // 计算书号
    private Date lossTime;                // 出险时间
    private Date endcaseTime;             // 结案时间
    private String payType;               // 事故处理类型
    private double totalAmount;          // 赔偿总额
    private String policyNo;              // 保单号
    private Date effectiveDate;          // 有效日期
    private Date expireDate;              // 失效日期
    private String claimNotificationNo;
    private String caseId;

    public String getPayCompany() {
        return payCompany;
    }

    public void setPayCompany(String payCompany) {
        this.payCompany = payCompany;
    }

    public String getClaimNo() {
        return claimNo;
    }

    public void setClaimNo(String claimNo) {
        this.claimNo = claimNo;
    }

    public String getCompensateNo() {
        return compensateNo;
    }

    public void setCompensateNo(String compensateNo) {
        this.compensateNo = compensateNo;
    }

    public Date getLossTime() {
        return lossTime;
    }

    public void setLossTime(Date lossTime) {
        this.lossTime = lossTime;
    }

    public Date getEndcaseTime() {
        return endcaseTime;
    }

    public void setEndcaseTime(Date endcaseTime) {
        this.endcaseTime = endcaseTime;
    }

    public String getPayType() {
        return payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public Date getEffectiveDate() {
        return effectiveDate;
    }

    public void setEffectiveDate(Date effectiveDate) {
        this.effectiveDate = effectiveDate;
    }

    public Date getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(Date expireDate) {
        this.expireDate = expireDate;
    }

    public String getClaimNotificationNo() {
        return claimNotificationNo;
    }

    public void setClaimNotificationNo(String claimNotificationNo) {
        this.claimNotificationNo = claimNotificationNo;
    }

    public String getCaseId() {
        return caseId;
    }

    public void setCaseId(String caseId) {
        this.caseId = caseId;
    }
}
