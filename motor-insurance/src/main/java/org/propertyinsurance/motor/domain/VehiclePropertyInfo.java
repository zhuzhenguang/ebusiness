package org.propertyinsurance.motor.domain;

/**
 * 车辆属性.
 *
 * User: zhenguang.zhu
 * Date: 12-12-8
 * Time: 下午11:49
 */
public class VehiclePropertyInfo {
    private String frameNo;           // 车架号
    private String engineNo;          // 发动机号
    private String brandName;         // 品牌名称
    private String vehicleCode;       // 车型代码
    private String enrollDate;        // 初登日期
    private String countryNature;     // 国产、进口、合资
    private String makeDate;           // 出厂日期
    private String detail;             // 详细描述/车型名称
    private String riskFlag;           // 风险标志
    private String vehicleModel;       // 品牌型号，detail中的英文
    private String manufacturerName;  // 制造厂商
    private String useNatureCode;      // 使用性质
    private String carKindCode;        // 车辆型号
    private String description;        // 车辆描述
    private RunArea runArea;           // 行驶区域
    private double runMiles;           // 行驶里程

    public String getFrameNo() {
        return frameNo;
    }

    public void setFrameNo(String frameNo) {
        this.frameNo = frameNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public void setEngineNo(String engineNo) {
        this.engineNo = engineNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public void setBrandName(String brandName) {
        this.brandName = brandName;
    }

    public String getVehicleCode() {
        return vehicleCode;
    }

    public void setVehicleCode(String vehicleCode) {
        this.vehicleCode = vehicleCode;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public void setEnrollDate(String enrollDate) {
        this.enrollDate = enrollDate;
    }

    public String getCountryNature() {
        return countryNature;
    }

    public void setCountryNature(String countryNature) {
        this.countryNature = countryNature;
    }

    public String getMakeDate() {
        return makeDate;
    }

    public void setMakeDate(String makeDate) {
        this.makeDate = makeDate;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail;
    }

    public String getRiskFlag() {
        return riskFlag;
    }

    public void setRiskFlag(String riskFlag) {
        this.riskFlag = riskFlag;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public void setVehicleModel(String vehicleModel) {
        this.vehicleModel = vehicleModel;
    }

    public String getManufacturerName() {
        return manufacturerName;
    }

    public void setManufacturerName(String manufacturerName) {
        this.manufacturerName = manufacturerName;
    }

    public String getUseNatureCode() {
        return useNatureCode;
    }

    public void setUseNatureCode(String useNatureCode) {
        this.useNatureCode = useNatureCode;
    }

    public String getCarKindCode() {
        return carKindCode;
    }

    public void setCarKindCode(String carKindCode) {
        this.carKindCode = carKindCode;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public RunArea getRunArea() {
        return runArea;
    }

    public void setRunArea(RunArea runArea) {
        this.runArea = runArea;
    }

    public double getRunMiles() {
        return runMiles;
    }

    public void setRunMiles(double runMiles) {
        this.runMiles = runMiles;
    }
}
