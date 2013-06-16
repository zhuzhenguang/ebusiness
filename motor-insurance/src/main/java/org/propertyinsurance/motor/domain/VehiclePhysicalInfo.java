package org.propertyinsurance.motor.domain;

/**
 * 车辆物理信息.
 *
 * User: zhenguang.zhu
 * Date: 12-12-8
 * Time: 下午11:46
 */
public class VehiclePhysicalInfo {
    private VehicleColor vehicleColor; // 车身颜色
    private int seatCount;            // 座位数
    private double tonCount;          // 吨位，核定载质量
    private double exhaustScale;      // 排量

    public VehicleColor getVehicleColor() {
        return vehicleColor;
    }

    public void setVehicleColor(VehicleColor vehicleColor) {
        this.vehicleColor = vehicleColor;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public void setSeatCount(int seatCount) {
        this.seatCount = seatCount;
    }

    public double getTonCount() {
        return tonCount;
    }

    public void setTonCount(double tonCount) {
        this.tonCount = tonCount;
    }

    public double getExhaustScale() {
        return exhaustScale;
    }

    public void setExhaustScale(double exhaustScale) {
        this.exhaustScale = exhaustScale;
    }
}
