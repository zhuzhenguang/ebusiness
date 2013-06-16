package org.propertyinsurance.motor.domain.area;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.Errors;
import org.propertyinsurance.motor.domain.InsuranceType;
import org.apache.commons.lang.ArrayUtils;

/**
 * 车辆所在地区.
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 上午12:05
 */
public abstract class Area {
    private static final String BASE_PACKAGE = "org.propertyinsurance.motor.domain.area.";
    private static final String CLASS_STRING = "Area";

    private Value value; // 代表城市的枚举类型

    /**
     * 创建地区基类
     *
     * @param value
     * @return
     * @throws BusinessException
     */
    public static Area create(Value value) throws BusinessException {
        try {
            Class<?> areaClass = getAreaRelateClass(value, "", CLASS_STRING);
            return (Area) ConstructorAccess.get(areaClass).newInstance(value);
        } catch (ClassNotFoundException e) {
            throw BusinessException.create(Errors.AERA_ERROR);
        }
    }

    /**
     * 创建和地区有关的Class，用于反射
     *
     * @param areaValue
     * @param prefix
     * @param suffix
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getAreaRelateClass(Value areaValue, String prefix, String suffix)
            throws ClassNotFoundException {
        return Class.forName(BASE_PACKAGE + prefix + areaValue.getAreaCode() + suffix);
    }

    /**
     * 必须用一个枚举创建城市
     *
     * @param value
     */
    protected Area(Value value) {
        this.value = value;
    }

    /**
     * 根据地区选择投保、车型查询的前缀
     *
     * @return 前缀
     */
    public String getPrefix() {
        if (!isCorrectArea()) {
            throw BusinessException.create(Errors.AERA_ERROR);
        }
        return getSimple();
    }

    /**
     * 获得城市的简写
     *
     * @return
     */
    protected abstract String getSimple();

    /**
     * 是否是一个可以接受的城市
     *
     * @return
     */
    protected boolean isCorrectArea() {
        return ArrayUtils.contains(Value.values(), getValue());
    }

    /**
     * 获得保险合法提前期
     *
     * @param insuranceType
     * @return 天数
     */
    public abstract int getInsuranceLeadDays(InsuranceType insuranceType);

    /**
     * 获得保险重复投保日期
     *
     * @return
     */
    public abstract int getDoubleInsuranceDays();

    /**
     * 获得城市枚举
     *
     * @return
     */
    public Value getValue() {
        return value;
    }

    /**
     * 城市代表
     */
    public static enum Value {
        ShangHai("021", "上海"), SuZhou("0512", "苏州");

        private String areaCode;
        private String areaName;

        private Value(String areaCode, String areaName) {
            this.areaCode = areaCode;
            this.areaName = areaName;
        }

        public String getAreaCode() {
            return areaCode;
        }

        public String getAreaName() {
            return areaName;
        }
    }
}
