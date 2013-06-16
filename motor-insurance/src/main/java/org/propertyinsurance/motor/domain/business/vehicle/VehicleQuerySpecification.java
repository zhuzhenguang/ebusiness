package org.propertyinsurance.motor.domain.business.vehicle;

import org.propertyinsurance.motor.domain.area.Area;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.Specification;

/**
 * 车型查询条件
 *
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 下午8:43
 */
public class VehicleQuerySpecification extends Specification {
    private static final String LICENSE_KIND_CODE = "02";
    private static final String SALES_CHANNEL_CODE = "PISUNG0480";
    private static final String VEHICLE_STYLE_DESC = "小型轿车";
    public static final String CAR_QUERY_SUFFIX = "CarQuery";

    private String licenseNo;
    private boolean newCar;
    private String carOwner;
    private boolean notShangHaiLicense;
    private String salesChannelCode;
    private String frameNo;
    private String engineNo;
    private String brandName;
    private String vehicleMode;
    private String enrollDate;
    private String licenseKindCode;
    private String vehicleStyleDesc;
    private VehicleQueryResultMatcher resultMatcher; // 车型匹配器，用于车型查询结果匹配

    private VehicleQuerySpecification() {
        this.licenseKindCode = LICENSE_KIND_CODE;
        this.salesChannelCode = SALES_CHANNEL_CODE;
    }

    private VehicleQuerySpecification(Area area, String carOwner) {
        this();
        this.area = area;
        this.carOwner = carOwner;
    }

    private VehicleQuerySpecification(VehicleInfo vehicleInfo) {
        this();
        this.frameNo = vehicleInfo.getPropertyInfo().getFrameNo();
        this.engineNo = vehicleInfo.getPropertyInfo().getEngineNo();
        this.brandName = vehicleInfo.getPropertyInfo().getBrandName();
        this.vehicleMode = vehicleInfo.getPropertyInfo().getVehicleCode();
        this.enrollDate = vehicleInfo.getPropertyInfo().getEnrollDate();
        this.area = vehicleInfo.getArea();
        this.resultMatcher = vehicleInfo.getVehicleMatcher();
        this.carOwner = vehicleInfo.getCarOwner();
    }

    /**
     * 上海沪牌的查询条件
     *
     * @param vehicleInfo 车辆信息
     * @return 查询条件
     */
    public static VehicleQuerySpecification getForShangHaiLicense(VehicleInfo vehicleInfo) {
        VehicleQuerySpecification specification = new VehicleQuerySpecification(
                vehicleInfo.getArea(), vehicleInfo.getCarOwner());
        specification.setLicenseNo(vehicleInfo.getLicenseNo());
        specification.setNewCar(false);
        specification.setNotShangHaiLicense(false);
        return specification;
    }

    /**
     * 上海非沪牌的查询条件
     *
     * @param vehicleInfo 车辆信息
     * @return 查询条件
     */
    public static VehicleQuerySpecification getForShangHaiNonLicense(VehicleInfo vehicleInfo) {
        VehicleQuerySpecification specification = new VehicleQuerySpecification(vehicleInfo);
        specification.setLicenseNo(vehicleInfo.getLicenseNo());
        specification.setNewCar(false);
        specification.setNotShangHaiLicense(true);
        specification.setVehicleStyleDesc(VEHICLE_STYLE_DESC);
        return specification;
    }

    /**
     * 上海新车的查询条件
     *
     * @return 查询条件
     */
    public static VehicleQuerySpecification getForNew(VehicleInfo vehicleInfo) {
        VehicleQuerySpecification specification = new VehicleQuerySpecification(vehicleInfo);
        specification.setLicenseNo("");
        specification.setNewCar(true);
        specification.setNotShangHaiLicense(false);
        return specification;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    private void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public boolean isNewCar() {
        return newCar;
    }

    private void setNewCar(boolean newCar) {
        this.newCar = newCar;
    }

    public boolean isNotShangHaiLicense() {
        return notShangHaiLicense;
    }

    public void setNotShangHaiLicense(boolean notShangHaiLicense) {
        this.notShangHaiLicense = notShangHaiLicense;
    }

    public String getSalesChannelCode() {
        return salesChannelCode;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getVehicleMode() {
        return vehicleMode;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public String getLicenseKindCode() {
        return licenseKindCode;
    }

    public String getVehicleStyleDesc() {
        return vehicleStyleDesc;
    }

    public void setVehicleStyleDesc(String vehicleStyleDesc) {
        this.vehicleStyleDesc = vehicleStyleDesc;
    }

    public Area getArea() {
        return area;
    }

    public VehicleQueryResultMatcher getResultMatcher() {
        return resultMatcher;
    }

    public void setResultMatcher(VehicleQueryResultMatcher resultMatcher) {
        this.resultMatcher = resultMatcher;
    }

    public String getCarOwner() {
        return carOwner;
    }
}
