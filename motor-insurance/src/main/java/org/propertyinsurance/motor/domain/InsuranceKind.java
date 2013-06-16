package org.propertyinsurance.motor.domain;

import org.apache.commons.lang.StringUtils;

/**
 * 投保险别
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 下午2:41
 */
public class InsuranceKind {
    private String kindCode;              // 险别代码
    private String kindName;              // 险别名称
    private String currency;              // 币别
    private double unitAmount;           // 单位保险金额
    private int quantity;                 // 数量
    private double amount;                // 保险金额
    private double deductibleRate;       // 免赔率
    private double rate;                  // 费率
    private double basePremium;          // 基准保费
    private double benchMarkPremium;     //标准保费
    private double discount;              // 折扣率
    private double adjustRate;            // 保费调整比率
    private double premium;               // 保费
    private boolean waiverOfDeductible; // 不计免赔

    /**
     * 设置不计免赔
     *
     * @param code 核心传值
     */
    public void setWaiverOfDeductible(String code) {
        if (StringUtils.isBlank(code)) {
            return;
        }
        setWaiverOfDeductible("1".equals(code));
    }

    public String getKindCode() {
        return kindCode;
    }

    public void setKindCode(String kindCode) {
        this.kindCode = kindCode;
    }

    public String getKindName() {
        return kindName;
    }

    public void setKindName(String kindName) {
        this.kindName = kindName;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    public double getUnitAmount() {
        return unitAmount;
    }

    public void setUnitAmount(double unitAmount) {
        this.unitAmount = unitAmount;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    public double getDeductibleRate() {
        return deductibleRate;
    }

    public void setDeductibleRate(double deductibleRate) {
        this.deductibleRate = deductibleRate;
    }

    public double getRate() {
        return rate;
    }

    public void setRate(double rate) {
        this.rate = rate;
    }

    public double getBasePremium() {
        return basePremium;
    }

    public void setBasePremium(double basePremium) {
        this.basePremium = basePremium;
    }

    public double getBenchMarkPremium() {
        return benchMarkPremium;
    }

    public void setBenchMarkPremium(double benchMarkPremium) {
        this.benchMarkPremium = benchMarkPremium;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getAdjustRate() {
        return adjustRate;
    }

    public void setAdjustRate(double adjustRate) {
        this.adjustRate = adjustRate;
    }

    public double getPremium() {
        return premium;
    }

    public void setPremium(double premium) {
        this.premium = premium;
    }

    public boolean isWaiverOfDeductible() {
        return waiverOfDeductible;
    }

    public void setWaiverOfDeductible(boolean waiverOfDeductible) {
        this.waiverOfDeductible = waiverOfDeductible;
    }
}
