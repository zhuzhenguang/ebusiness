package org.propertyinsurance.motor.domain;

/**
 * 保险关系人类型
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-8-31
 * Time: 下午4:46
 */
public enum InsuredType {
    Assured("1"), Proposal("2");// 被保险人和投保人

    private String code;

    private InsuredType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static InsuredType get(String code) {
        if ("1".equals(code)) {
            return Assured;
        } else if ("2".equals(code)) {
            return Proposal;
        } else {
            throw new IllegalArgumentException("代码错误");
        }
    }
}
