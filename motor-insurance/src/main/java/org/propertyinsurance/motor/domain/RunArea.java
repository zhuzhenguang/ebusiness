package org.propertyinsurance.motor.domain;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 行驶区域.
 *
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 上午12:13
 */
public enum RunArea {
    InnerProvince("03"), InnerCountry("04");

    private static Map<String, RunArea> codeMap = ImmutableMap.of(
            "03", InnerProvince, // 省内
            "04", InnerCountry); // 全国
    private String code;

    private RunArea(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static RunArea get(String code) {
        return codeMap.get(code);
    }
}
