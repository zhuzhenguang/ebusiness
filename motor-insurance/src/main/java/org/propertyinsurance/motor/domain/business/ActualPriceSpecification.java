package org.propertyinsurance.motor.domain.business;

import org.propertyinsurance.motor.Assert;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.apache.commons.lang.StringUtils;

/**
 * 实际价格查询条件
 *
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 下午8:53
 */
public class ActualPriceSpecification extends Specification {
    private static final String CLAUSE_TYPE = "F46";
    private static final String USE_TYPE = "8A";
    private static final String CAR_KIND_CODE = "A0";

    private String clauseType;
    private String carKindCode;
    private int seatCount;
    private double tonCount;
    private String useNatureCode;
    private String enrollDate;
    private String operateDate;
    private String startDate;
    private double purchasePrice;

    public ActualPriceSpecification(VehicleInfo vehicleInfo, String date) {
        Assert.notNull(vehicleInfo);

        this.seatCount = vehicleInfo.getPhysicalInfo().getSeatCount();
        this.tonCount = vehicleInfo.getPhysicalInfo().getTonCount();
        this.enrollDate = vehicleInfo.getPropertyInfo().getEnrollDate();
        this.purchasePrice = vehicleInfo.getPriceInfo().getPurchasePrice();
        this.clauseType = CLAUSE_TYPE;
        this.carKindCode = CAR_KIND_CODE;
        this.useNatureCode = USE_TYPE;
        this.operateDate = DateUtil.getCurrentDateString();
        this.startDate = StringUtils.isNotBlank(date) ? date : DateUtil.getTodayPlus2DateString();
    }

    public String getClauseType() {
        return clauseType;
    }

    public String getCarKindCode() {
        return carKindCode;
    }

    public int getSeatCount() {
        return seatCount;
    }

    public double getTonCount() {
        return tonCount;
    }

    public String getUseNatureCode() {
        return useNatureCode;
    }

    public String getEnrollDate() {
        return enrollDate;
    }

    public String getOperateDate() {
        return operateDate;
    }

    public String getStartDate() {
        return startDate;
    }

    public double getPurchasePrice() {
        return purchasePrice;
    }
}
