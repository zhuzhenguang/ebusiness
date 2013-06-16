package org.propertyinsurance.motor.domain;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.domain.area.Area;

import java.util.Date;

/**
 * 保险期间
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-8-30
 * Time: 下午4:43
 */
public class InsurancePeriod {
    private Date commercialStartDate;                         // 商业险起保日期
    private Date commercialEndDate;                           // 商业险结束日期
    private Date trafficStartDate;                            // 交强险起保日期
    private Date trafficEndDate;                              // 交强险结束日期
    private boolean doubleInsurance = false;                 // 是否重复投保
    private boolean trafficInsuring = false;                 // 交强险是否在保

    /**
     * 检查保险期间合法性
     *
     * @param area 地域
     * @return
     */
    public boolean checkLeadTime(Area area) throws BusinessException {
        return InsurancePeriodLeadTime.checkLeadTime(area, this);
    }

    /**
     * 检查是否脱保
     *
     * @return
     * @throws BusinessException
     */
    public boolean checkInsuranceExpire() throws BusinessException {
        return InsuranceExpire.checkInsuranceExpire(this);
    }

    /**
     * 检查交强险是否重复投保
     *
     * @param area
     * @return
     */
    public boolean checkDoubleInsurance(Area area) {
        Date day = getTrafficEndDate() == null ? DateUtil.getTodayPlus2Date() : getTrafficEndDate();
        setDoubleInsurance(DateUtil.diffDays(day, new Date()) > area.getDoubleInsuranceDays());
        return isDoubleInsurance();
    }

    public Date getCommercialStartDate() {
        return commercialStartDate;
    }

    public void setCommercialStartDate(Date commercialStartDate) {
        this.commercialStartDate = commercialStartDate;
    }

    public Date getCommercialEndDate() {
        return commercialEndDate;
    }

    public void setCommercialEndDate(Date commercialEndDate) {
        this.commercialEndDate = commercialEndDate;
    }

    public Date getTrafficStartDate() {
        return trafficStartDate;
    }

    public void setTrafficStartDate(Date trafficStartDate) {
        this.trafficStartDate = trafficStartDate;
    }

    public Date getTrafficEndDate() {
        return trafficEndDate;
    }

    public void setTrafficEndDate(Date trafficEndDate) {
        this.trafficEndDate = trafficEndDate;
    }

    public boolean isDoubleInsurance() {
        return doubleInsurance;
    }

    public void setDoubleInsurance(boolean doubleInsurance) {
        this.doubleInsurance = doubleInsurance;
    }

    public boolean isTrafficInsuring() {
        return trafficInsuring;
    }

    public void setTrafficInsuring(boolean trafficInsuring) {
        this.trafficInsuring = trafficInsuring;
    }

}
