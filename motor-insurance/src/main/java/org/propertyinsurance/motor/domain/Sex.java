package org.propertyinsurance.motor.domain;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 性别
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-9-5
 * Time: 上午9:26
 */
public enum Sex {
    Male("1"), Female("2");

    private static Map<String, Sex> sexMap = ImmutableMap.of(
            "1", Male,    // 男
            "2", Female); // 女
    private String code;

    private Sex(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static Sex get(String code) {
        return sexMap.get(code);
    }
}
