package org.propertyinsurance.motor.domain;

import com.google.common.collect.ImmutableMap;

import java.util.Map;

/**
 * 驾驶人类型
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-9-5
 * Time: 上午9:18
 */
public enum DriverType {
    MainDriver("1"), AssistantDriver1("2"), AssistantDriver2("3");

    private String code;
    private static Map<String, DriverType> driverTypeMap = ImmutableMap.of(
            "1", MainDriver,         // 主驾驶人
            "2", AssistantDriver1,  // 副驾驶1
            "3", AssistantDriver2); // 副驾驶人2

    private DriverType(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }

    public static DriverType get(String code) {
        return driverTypeMap.get(code);
    }
}
