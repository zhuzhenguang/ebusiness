package org.propertyinsurance.motor.domain.area;

import com.google.common.collect.ImmutableMap;
import org.propertyinsurance.motor.domain.InsuranceType;

import java.util.Map;

/**
 * 苏州地区业务
 *
 * User: zhenguang.zhu
 * Date: 13-1-15
 * Time: 下午11:15
 */
class SuZhouArea extends Area{
    private static final String SIMPLE = "SZ";
    private Map<InsuranceType, Integer> leadTimeMap = ImmutableMap.of(
            InsuranceType.Commercial, 40, InsuranceType.Traffic, 40);
    private static final int DOUBLE_INSURANCE_DAYS = 40;

    /**
     * 必须用一个枚举创建城市
     *
     * @param value
     */
    private SuZhouArea(Value value) {
        super(value);
    }

    @Override
    protected String getSimple() {
        return SIMPLE;
    }

    @Override
    public int getInsuranceLeadDays(InsuranceType insuranceType) {
        return leadTimeMap.get(insuranceType);
    }

    @Override
    public int getDoubleInsuranceDays() {
        return DOUBLE_INSURANCE_DAYS;
    }
}
