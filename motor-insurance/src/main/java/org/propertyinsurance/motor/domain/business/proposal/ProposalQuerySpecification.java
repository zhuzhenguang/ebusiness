package org.propertyinsurance.motor.domain.business.proposal;

import org.propertyinsurance.motor.domain.area.Area;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.Specification;

/**
 * 投保查询
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-27
 * Time: 下午11:22
 */
public class ProposalQuerySpecification extends Specification {
    private static final String LICENSE_KIND_CODE = "02";
    private static final String CAR_KIND_CODE = "A0";
    private static final String USE_NATURE_CODE = "";
    private static final String SALES_CHANNEL_CODE = "PISUNG0480";
    private static final String PAY_METHOD = "07";
    private static final String VEHICLE_STYLE_DESC = "小型轿车";

    private String licenseNo;
    private String licenseKindCode;
    private String carKindCode;
    private String useNatureCode;
    private String salesChannelCode;
    private boolean newCar;
    private int seatCount;
    private String payMethod;
    private String brandName;
    private String vehicleModel;
    private String vehicleStyleDesc;
    private String enrollDate;
    private String frameNo;
    private String engineNo;
    private int selectedNo;

    private ProposalQuerySpecification(VehicleInfo vehicleInfo) {
        this(vehicleInfo.getPhysicalInfo().getSeatCount(), vehicleInfo.getArea(),
                vehicleInfo.getVehicleInfoExt().getSelectedNo());
        this.brandName = vehicleInfo.getPropertyInfo().getBrandName();
        this.enrollDate = vehicleInfo.getPropertyInfo().getEnrollDate();
        this.frameNo = vehicleInfo.getPropertyInfo().getFrameNo();
        this.engineNo = vehicleInfo.getPropertyInfo().getEngineNo();
        this.vehicleModel = vehicleInfo.getPropertyInfo().getVehicleModel();
        this.newCar = vehicleInfo.isNewCar();
        this.licenseNo = vehicleInfo.getLicenseNo();
    }

    private ProposalQuerySpecification(int seatCount, Area area, int selectedNo) {
        this.seatCount = seatCount;
        this.area = area;
        this.selectedNo = selectedNo;
        this.licenseKindCode = LICENSE_KIND_CODE;
        this.carKindCode = CAR_KIND_CODE;
        this.useNatureCode = USE_NATURE_CODE;
        this.salesChannelCode = SALES_CHANNEL_CODE;
        this.payMethod = PAY_METHOD;
        this.vehicleStyleDesc = VEHICLE_STYLE_DESC;
    }

    /**
     * 新车的投保查询
     *
     * @param vehicleInfo
     * @return
     */
    public static ProposalQuerySpecification createForNew(VehicleInfo vehicleInfo) {
        ProposalQuerySpecification specification = new ProposalQuerySpecification(vehicleInfo);
        specification.newCar = true;
        specification.licenseNo = "";
        return specification;
    }

    /**
     * 上海非沪牌的投保查询
     *
     * @param vehicleInfo
     * @return
     */
    public static ProposalQuerySpecification createForNotShangHaiLicense(VehicleInfo vehicleInfo) {
        return new ProposalQuerySpecification(vehicleInfo);
    }

    /**
     * 上海沪牌的投保查询
     *
     * @param vehicleInfo
     * @return
     */
    public static ProposalQuerySpecification createForShangHaiLicense(VehicleInfo vehicleInfo) {
        int seatCount = vehicleInfo.getPhysicalInfo().getSeatCount();
        Area area = vehicleInfo.getArea();
        int selectedNo = vehicleInfo.getVehicleInfoExt().getSelectedNo();
        return new ProposalQuerySpecification(seatCount, area, selectedNo);
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getLicenseKindCode() {
        return licenseKindCode;
    }

    public String getCarKindCode() {
        return carKindCode;
    }

    public String getUseNatureCode() {
        return useNatureCode;
    }

    public String getSalesChannelCode() {
        return salesChannelCode;
    }

    public boolean isNewCar() {
        return newCar;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public String getPayMethod() {
        return payMethod;
    }

    public String getBrandName() {
        return brandName;
    }

    public String getVehicleModel() {
        return vehicleModel;
    }

    public String getVehicleStyleDesc() {
        return vehicleStyleDesc;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public int getSelectedNo() {
        return selectedNo;
    }
}
