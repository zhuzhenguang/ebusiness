package org.propertyinsurance.motor.domain.business;

import org.propertyinsurance.motor.domain.area.Area;

/**
 * User: zhenguang.zhu
 * Date: 12-12-23
 * Time: 下午6:18
 */
public abstract class Specification {
    protected Area area;

    public Area getArea() {
        return area;
    }
}
