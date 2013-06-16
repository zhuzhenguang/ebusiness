package org.propertyinsurance.motor.domain;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.Errors;

import java.util.Date;

/**
 * 脱保业务
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-9-6
 * Time: 上午11:02
 */
public abstract class InsuranceExpire {
    private String expireDate;       // 脱保日期
    private String currentDate;      // 当期日期
    private ExpireType expireType;   // 脱保类型：已经脱保，差1天脱保
    private boolean expire = false; // 是否脱保
    private InsuranceType insuranceType; // 脱保业务类型

    protected InsuranceExpire(InsuranceType insuranceType) {
        this.insuranceType = insuranceType;
    }

    /**
     * 根据业务类型不同创建脱保业务
     *
     * @param insuranceType
     * @return
     */
    public static InsuranceExpire create(InsuranceType insuranceType) {
        switch (insuranceType) {
            case Commercial:
                return new CommercialExpire(insuranceType);
            case Traffic:
                return new TrafficExpire(insuranceType);
            default:
                throw new BusinessException("保险类型错误");
        }
    }

    /**
     * 检查脱保
     *
     * @param insurancePeriod
     * @return
     * @throws BusinessException
     */
    public static boolean checkInsuranceExpire(InsurancePeriod insurancePeriod) throws BusinessException {
        InsuranceExpire commercialExpire = create(InsuranceType.Commercial);
        InsuranceExpire trafficExpire = create(InsuranceType.Traffic);
        if (commercialExpire.check(insurancePeriod) || trafficExpire.check(insurancePeriod)) {
            BusinessException businessException = BusinessException.create(Errors.EXPIRE_ERROR);
            commercialExpire.setException(businessException);
            trafficExpire.setException(businessException);
            throw businessException;
        } else  {
            return false;
        }

    }

    /**
     * 检查脱保
     *
     * @param insurancePeriod
     * @return
     */
    public boolean check(InsurancePeriod insurancePeriod) {
        Date endDate = getEndDate(insurancePeriod);
        long days = DateUtil.diffDayAndToday(endDate);
        if (days >= 0) {
            setExpire(true);// 脱保标志
            setCurrentDate(DateUtil.convertDateToString(new Date()));// 当期日期
            setExpireDate(DateUtil.convertDateToString(endDate));    // 脱保日期
            setExpireType(days);
        }
        return isExpire();
    }

    /**
     * 设置脱保类型
     *
     * @param days
     */
    protected void setExpireType(long days) {
        if (days > 0) {
            setExpireType(ExpireType.HasExpire);
        } else if (days == 0) {
            setExpireType(ExpireType.OneDayExpire);
        }
    }

    /**
     * 获得比较日期
     *
     * @param insurancePeriod
     * @return
     */
    protected abstract Date getEndDate(InsurancePeriod insurancePeriod);

    /**
     * 增加异常
     *
     * @param businessException
     */
    public void setException(BusinessException businessException) {
        if (isExpire()) {
            businessException.addExceptionObject(this);
        }
    }

    public InsuranceType getInsuranceType() {
        return insuranceType;
    }

    public String getCurrentDate() {
        return currentDate;
    }

    public void setCurrentDate(String currentDate) {
        this.currentDate = currentDate;
    }

    public boolean isExpire() {
        return expire;
    }

    public void setExpire(boolean expire) {
        this.expire = expire;
    }

    public String getExpireDate() {
        return expireDate;
    }

    public void setExpireDate(String expireDate) {
        this.expireDate = expireDate;
    }

    public ExpireType getExpireType() {
        return expireType;
    }

    public void setExpireType(ExpireType expireType) {
        this.expireType = expireType;
    }
}

/**
 * 商业险脱保业务
 */
class CommercialExpire extends InsuranceExpire {
    protected CommercialExpire(InsuranceType insuranceType) {
        super(insuranceType);
    }

    @Override
    protected Date getEndDate(InsurancePeriod insurancePeriod) {
        return insurancePeriod.getCommercialEndDate() == null ?
                DateUtil.getTodayPlus2Date() :
                insurancePeriod.getCommercialEndDate();
    }
}

/**
 * 交强险脱保业务
 */
class TrafficExpire extends InsuranceExpire {
    protected TrafficExpire(InsuranceType insuranceType) {
        super(insuranceType);
    }

    @Override
    protected Date getEndDate(InsurancePeriod insurancePeriod) {
        return insurancePeriod.getTrafficEndDate() == null ?
                DateUtil.getTodayPlus2Date() :
                insurancePeriod.getTrafficEndDate();
    }
}
