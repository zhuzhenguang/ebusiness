package org.propertyinsurance.motor.domain;

/**
 * 包含所有属性
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:18
 */
public class CommonInfo {
    private VehicleInfo vehicleInfo;       // 车辆信息
    private InsuranceInfo insuranceInfo;   // 一般保险
    private PremiumInfo premiumInfo;       // 保费信息

    private CommonInfo(VehicleInfo vehicleInfo, InsuranceInfo insuranceInfo) {
        this.vehicleInfo = vehicleInfo;
        this.insuranceInfo = insuranceInfo;
    }

    public CommonInfo(VehicleInfo vehicleInfo, InsuranceInfo insuranceInfo, PremiumInfo premiumInfo) {
        this(vehicleInfo, insuranceInfo);
        this.premiumInfo = premiumInfo;
    }

    /**
     * 使用车辆信息和保险信息创造
     *
     * @param vehicleInfo
     * @param insuranceInfo
     * @return
     */
    public static CommonInfo createWithVehicleAndInsurance(VehicleInfo vehicleInfo,
                                                           InsuranceInfo insuranceInfo) {
        return new CommonInfo(vehicleInfo, insuranceInfo);
    }

    /**
     * 使用车辆信息,保险信息和保费信息创造
     *
     * @param vehicleInfo
     * @param insuranceInfo
     * @return
     */
    public static CommonInfo createWithVehicleInsuranceAndPremium(VehicleInfo vehicleInfo,
                                                                  InsuranceInfo insuranceInfo,
                                                                  PremiumInfo premiumInfo) {
        return new CommonInfo(vehicleInfo, insuranceInfo, premiumInfo);
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    public InsuranceInfo getInsuranceInfo() {
        return insuranceInfo;
    }

    public PremiumInfo getPremiumInfo() {
        return premiumInfo;
    }
}
