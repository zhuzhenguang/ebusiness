package org.propertyinsurance.motor.domain.business;

/**
 * 续保查询
 *
 * User: zhenguang.zhu
 * Date: 13-1-16
 * Time: 上午12:23
 */
public class RenewalSpecification extends Specification {
    private String carOwner;
    private String frameNo;
    private String engineNo;
    private String startDate;
    private String endDate;

    public RenewalSpecification(String carOwner, String frameNo,
                                String engineNo, String startDate,
                                String endDate) {
        this.carOwner = carOwner;
        this.frameNo = frameNo;
        this.engineNo = engineNo;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public String getCarOwner() {
        return carOwner;
    }

    public String getFrameNo() {
        return frameNo;
    }

    public String getEngineNo() {
        return engineNo;
    }

    public String getStartDate() {
        return startDate;
    }

    public String getEndDate() {
        return endDate;
    }
}
