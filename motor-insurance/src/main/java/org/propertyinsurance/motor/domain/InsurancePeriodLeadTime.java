package org.propertyinsurance.motor.domain;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.Errors;
import org.propertyinsurance.motor.domain.area.Area;

import java.util.Date;

/**
 * 保险期间提前期审查
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-8-16
 * Time: 上午11:14
 */
public abstract class InsurancePeriodLeadTime {
    private boolean out = true; // 是否到了提前期
    private String expireDate;  // 到期日
    private long expireDays = -1;   // 还有几天到期
    private InsuranceType type; // 到期的保险类型，商业还InsuranceType是交强险

    protected InsurancePeriodLeadTime(InsuranceType type) {
        this.type = type;
    }

    /**
     * 根据地区的不同检查方式也不同
     *
     * @return
     */
    public static InsurancePeriodLeadTime create(InsuranceType type) {
        switch (type) {
            case Commercial:
                return new CommercialLeadTime(type);
            case Traffic:
                return new TrafficLeadTime(type);
            default:
                throw new BusinessException("保险类型错误");
        }
    }

    /**
     * 创建提前期对象，并校验
     *
     * @param area
     * @param insurancePeriod
     * @return
     * @throws BusinessException
     */
    public static boolean checkLeadTime(Area area, InsurancePeriod insurancePeriod)
            throws BusinessException {
        InsurancePeriodLeadTime commercialLeadTime = create(InsuranceType.Commercial);
        InsurancePeriodLeadTime trafficLeadTime = create(InsuranceType.Traffic);
        if (commercialLeadTime.check(area, insurancePeriod) && trafficLeadTime.check(area, insurancePeriod)) {
            return true;
        } else if (!commercialLeadTime.isOut()) {/* 商业险未到期，属于业务错误 */
            BusinessException businessException = BusinessException.create(Errors.INSURING_ERROR);
            commercialLeadTime.setException(businessException);
            throw businessException;
        } else {/* 交强险未到期，可以允许 */
            insurancePeriod.setTrafficInsuring(true);
            return true;
        }
    }

    /**
     * 检查保险提前期是否到了
     *
     * @param area
     * @param insurancePeriod
     * @return
     */
    public boolean check(Area area, InsurancePeriod insurancePeriod) {
        Date endDate = getEndDate(insurancePeriod);
        long days = DateUtil.diffTodayAndDay(endDate);
        if (days > getGivenDays(area)) {
            setOut(false);
            setExpireDays(days);
            setExpireDate(DateUtil.convertDateToString(endDate));
        }
        return isOut();
    }

    /**
     * 获得保险到期日
     *
     * @param insurancePeriod
     * @return
     */
    protected abstract Date getEndDate(InsurancePeriod insurancePeriod);

    /**
     * 获得比较日期
     *
     * @param area
     * @return
     */
    private   int getGivenDays(Area area) {
        return area.getInsuranceLeadDays(getType());
    }

    /**
     * 增加异常
     *
     * @param businessException
     */
    public void setException(BusinessException businessException) {
        if (isOut()) {
            businessException.addExceptionObject(this);
        }
    }

    public boolean isOut() {
        return out;
    }

    public void setOut(boolean out) {
        this.out = out;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public long getExpireDays() {
        return expireDays;
    }

    public void setExpireDays(long expireDays) {
        this.expireDays = expireDays;
    }

    public InsuranceType getType() {
        return type;
    }
}

/**
 * 商业险提前期
 */
class CommercialLeadTime extends InsurancePeriodLeadTime {
    protected CommercialLeadTime(InsuranceType type) {
        super(type);
    }

    @Override
    protected Date getEndDate(InsurancePeriod insurancePeriod) {
        return insurancePeriod.getCommercialEndDate() == null ?
                DateUtil.getTodayPlus2Date() :
                insurancePeriod.getCommercialEndDate();
    }

}

/**
 * 交强险提前期
 */
class TrafficLeadTime extends InsurancePeriodLeadTime {
    protected TrafficLeadTime(InsuranceType type) {
        super(type);
    }

    @Override
    protected Date getEndDate(InsurancePeriod insurancePeriod) {
        return insurancePeriod.getTrafficEndDate() == null ?
                DateUtil.getTodayPlus2Date() :
                insurancePeriod.getTrafficEndDate();
    }

}
