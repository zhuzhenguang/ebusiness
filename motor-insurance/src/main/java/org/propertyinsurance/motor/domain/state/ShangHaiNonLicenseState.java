package org.propertyinsurance.motor.domain.state;

/**
 * 上海非沪牌
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-16
 * Time: 上午11:36
 */
public class ShangHaiNonLicenseState extends State {
    public ShangHaiNonLicenseState(Value value) {
        super(value);
    }

    @Override
    protected String getVehicleMatcherKeyword() {
        return "Other";
    }
}
