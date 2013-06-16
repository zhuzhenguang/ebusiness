package org.propertyinsurance.motor.domain;

/**
 * 脱保类型
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-9-6
 * Time: 上午11:11
 */
public enum ExpireType {
    HasExpire("1"), OneDayExpire("2");

    private String code;

    private ExpireType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
