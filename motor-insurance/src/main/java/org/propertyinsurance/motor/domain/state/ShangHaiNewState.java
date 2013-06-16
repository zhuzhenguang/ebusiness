package org.propertyinsurance.motor.domain.state;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.VehicleInfo;

/**
 * 上海新车
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-16
 * Time: 上午11:38
 */
public class ShangHaiNewState extends State {
    public ShangHaiNewState(Value value) {
        super(value);
    }

    @Override
    protected String getVehicleMatcherKeyword() {
        return "Other";
    }

    @Override
    public CommonInfo queryBasicInfo(VehicleInfo vehicleInfo) throws BusinessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }

    @Override
    public CommonInfo queryVehicleInfo(VehicleInfo vehicleInfo) throws BusinessException {
        return null;  //To change body of implemented methods use File | Settings | File Templates.
    }
}
