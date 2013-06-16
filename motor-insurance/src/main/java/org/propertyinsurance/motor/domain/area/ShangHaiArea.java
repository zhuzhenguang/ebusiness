package org.propertyinsurance.motor.domain.area;

import com.google.common.collect.ImmutableMap;
import org.propertyinsurance.motor.domain.InsuranceType;

import java.util.Map;

/**
 * 上海业务
 *
 * User: zhenguang.zhu
 * Date: 13-1-15
 * Time: 下午11:14
 */
class ShangHaiArea extends Area {
    private static final String SIMPLE = "SH";
    private Map<InsuranceType, Integer> leadTimeMap = ImmutableMap.of(
            InsuranceType.Commercial, 60, InsuranceType.Traffic, 40);
    private static final int DOUBLE_INSURANCE_DAYS = 30;

    /**
     * 必须用一个枚举创建城市
     *
     * @param value
     */
    private ShangHaiArea(Value value) {
        super(value);
    }

    @Override
    protected String getSimple() {
        return SIMPLE;
    }

    @Override
    public int getInsuranceLeadDays(InsuranceType insuranceTypen) {
        return leadTimeMap.get(insuranceTypen);
    }

    @Override
    public int getDoubleInsuranceDays() {
        return DOUBLE_INSURANCE_DAYS;
    }

}
