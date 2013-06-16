package org.propertyinsurance.motor.domain;

/**
 * 保单查询类型
 * 01: 根据保单号查询保单信息
 * 02: 根据被保险人名和被保险人证件号码查询最近一次保单信息
 * 03: 根据被保险人名和被保险人证件号码查询被保险人所有保单信息
 * 04: 根据投保单号查询保单信息
 *
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 上午11:06
 */
public enum PolicyQueryType {
    QUERY_BY_POLICYNO("01"),
    QUERY_LAST_POLICYNO_BY_IDENTIFYNO("02"),
    QUERY_ALL_BY_IDENTIFYNO("03"),
    QUERY_BY_PROPOSALNO("04");

    private String value;

    private PolicyQueryType(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
