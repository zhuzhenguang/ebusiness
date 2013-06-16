package org.propertyinsurance.motor.domain;

/**
 * 保单数据标志
 * 1:所有保单数据
 * 2:只违法和理赔数据
 * 3:只收付信息
 *
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 上午11:05
 */
public enum PolicyDataFlag {
    ALL_POLICY("1"), VIOLATE_CLAIM("2"), PAYMENT("3");

    private String value;

    private PolicyDataFlag(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
