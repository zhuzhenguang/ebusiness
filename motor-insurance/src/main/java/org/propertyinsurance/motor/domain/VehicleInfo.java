package org.propertyinsurance.motor.domain;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.area.Area;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQueryResultMatcher;
import org.propertyinsurance.motor.domain.state.State;

/**
 * 车辆信息（其本身和保险没有关系）
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-8
 * Time: 下午11:39
 */
public class VehicleInfo {
    private String carOwner;          // 车主
    private String licenseNo;         // 车牌号
    private String licenseType;       // 号牌种类
    private boolean newCar;           // 是否新车
    private Area area;                 // 所在城市
    private String searchSequenceNo; // 车型查询码
    private State state;               // 业务状态

    private VehiclePhysicalInfo physicalInfo = new VehiclePhysicalInfo();
    private VehiclePropertyInfo propertyInfo = new VehiclePropertyInfo();
    private VehiclePriceInfo priceInfo = new VehiclePriceInfo();
    private VehicleInfoExt vehicleInfoExt = new VehicleInfoExt();

    public VehicleInfo() {
    }

    public VehicleInfo(Area area, State state) {
        this.area = area;
        this.state = state;
    }

    /**
     * 是否是上海车沪牌(核心需要)
     */
    public boolean isShangHaiLicense() {
        return area.getValue() == Area.Value.ShangHai &&
                !isNewCar() &&
                getLicenseNo().startsWith("沪");
    }

    /**
     * 获得一个车型查询结果匹配器
     */
    public VehicleQueryResultMatcher getVehicleMatcher() throws BusinessException {
        return getState().getVehicleMatcher(this);
    }

    public String getCarOwner() {
        return carOwner;
    }

    public void setCarOwner(String carOwner) {
        this.carOwner = carOwner;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getLicenseType() {
        return licenseType;
    }

    public void setLicenseType(String licenseType) {
        this.licenseType = licenseType;
    }

    public boolean isNewCar() {
        return newCar;
    }

    public void setNewCar(boolean newCar) {
        this.newCar = newCar;
    }

    public Area getArea() {
        return area;
    }

    public String getSearchSequenceNo() {
        return searchSequenceNo;
    }

    public void setSearchSequenceNo(String searchSequenceNo) {
        this.searchSequenceNo = searchSequenceNo;
    }

    public VehiclePhysicalInfo getPhysicalInfo() {
        return physicalInfo;
    }

    public void setPhysicalInfo(VehiclePhysicalInfo physicalInfo) {
        this.physicalInfo = physicalInfo;
    }

    public VehiclePropertyInfo getPropertyInfo() {
        return propertyInfo;
    }

    public void setPropertyInfo(VehiclePropertyInfo propertyInfo) {
        this.propertyInfo = propertyInfo;
    }

    public VehiclePriceInfo getPriceInfo() {
        return priceInfo;
    }

    public void setPriceInfo(VehiclePriceInfo priceInfo) {
        this.priceInfo = priceInfo;
    }

    public VehicleInfoExt getVehicleInfoExt() {
        return vehicleInfoExt;
    }

    public void setVehicleInfoExt(VehicleInfoExt vehicleInfoExt) {
        this.vehicleInfoExt = vehicleInfoExt;
    }

    public State getState() {
        return state;
    }

}
