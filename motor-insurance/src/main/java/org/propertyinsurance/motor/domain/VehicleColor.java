package org.propertyinsurance.motor.domain;

import com.google.common.collect.Maps;
import org.apache.commons.lang.StringUtils;

import java.util.Map;

/**
 * 车身颜色
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:01
 */
public enum VehicleColor {
    WHITE("03"), GREY("07"), YELLOW("04"),
    PINK("10"), RED("06"), PURPLE("11"),
    GREEN("08"), BLUE("01"), BROWN("09"),
    BLACK("02"), NULL("");

    private static Map<String, VehicleColor> codeMap = Maps.newHashMapWithExpectedSize(11);

    static {
        codeMap.put("A", WHITE);
        codeMap.put("B", GREY);
        codeMap.put("C", YELLOW);
        codeMap.put("D", PINK);
        codeMap.put("E", RED);
        codeMap.put("F", PURPLE);
        codeMap.put("G", GREEN);
        codeMap.put("H", BLUE);
        codeMap.put("I", BROWN);
        codeMap.put("J", BLACK);
        codeMap.put("default", NULL);
    }

    private String value;

    private VehicleColor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public static VehicleColor get(String code) {
        return codeMap.get(StringUtils.defaultIfEmpty(code, "default"));
    }
}
