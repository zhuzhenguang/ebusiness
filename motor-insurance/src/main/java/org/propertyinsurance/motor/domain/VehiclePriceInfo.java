package org.propertyinsurance.motor.domain;

/**
 * 车辆的价格信息.
 *
 * User: zhenguang.zhu
 * Date: 12-12-8
 * Time: 下午11:56
 */
public class VehiclePriceInfo {
    private double purchasePrice;    // 新车购置价
    private double actualPrice;      // 实际价格
    private boolean priced;           // 是否定价

    public double getPurchasePrice() {
        return purchasePrice;
    }

    public void setPurchasePrice(double purchasePrice) {
        this.purchasePrice = purchasePrice;
    }

    public double getActualPrice() {
        return actualPrice;
    }

    public void setActualPrice(double actualPrice) {
        this.actualPrice = actualPrice;
    }

    public boolean isPriced() {
        return priced;
    }

    public void setPriced(boolean priced) {
        this.priced = priced;
    }
}
