package org.propertyinsurance.motor.domain.business.vehicle;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.propertyinsurance.motor.Assert;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.VehicleInfoExt;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.VehiclePriceOutputDto;

/**
 * 车型查询结果匹配器
 * 一。不是上海沪牌的规则：
 * 1. 如果有精友code，就用精友code匹配，
 * 2. 如果精友code没有匹配上，用户又没有选择，就返回给前台让用户选择
 * 3. 用户选择后，就用用户选择的车
 * 二。上海沪牌的规则
 * 1. 如果精友code没有匹配上，用户又没有选择，就返回给前台让用户选择
 * 2. 用户选择后，就用用户选择的车
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-8-24
 * Time: 上午9:01
 */
public abstract class VehicleQueryResultMatcher {
    public static final String BASE_PACKAGE = "org.propertyinsurance.motor.domain.business.";
    private VehicleInfo vehicleInfo;                // 车辆扩展信息

    protected VehicleQueryResultMatcher(VehicleInfo vehicleInfo) {
        this.vehicleInfo = vehicleInfo;
    }

    /**
     * 通过业务不同，匹配器也不同
     *
     * @param vehicleInfo   车辆信息
     * @return 匹配器
     */
    public static VehicleQueryResultMatcher create(VehicleInfo vehicleInfo, String stateKeyword)
            throws BusinessException {
        ConstructorAccess<?> access = ConstructorAccess.get(getVehicleMatcherClass(stateKeyword));
        return (VehicleQueryResultMatcher) access.newInstance(vehicleInfo);
    }

    /*
     * 获得匹配器类名称
     */
    private static Class<?> getVehicleMatcherClass(String keyword) throws BusinessException {
        try {
            return Class.forName(BASE_PACKAGE + keyword + "Matcher");
        } catch (ClassNotFoundException e) {
            throw new BusinessException("车型匹配器初始化错误！");
        }
    }

    /**
     * 获得扩展信息
     *
     * @return
     */
    protected VehicleInfoExt getVehicleInfoExt() {
        return this.vehicleInfo.getVehicleInfoExt();
    }

    /**
     * 获得用户选择的车辆信息
     *
     * @param vehicleResult
     * @return
     */
    protected VehiclePriceOutputDto getUserSelectVehicleResult(VehiclePriceOutputDto[] vehicleResult) {
        Assert.notEmpty(vehicleResult);

        if (getVehicleInfoExt().isNeedSelect()) {// 需要用户选择
            return null;
        } else {                                 // 用户已经选择过了，或者不需要选择
            getVehicleInfoExt().setVehiclePrices(null);
            return vehicleResult[getVehicleInfoExt().getSelectedNo() - 1];
        }
    }

    /**
     * 匹配车型查询结果
     *
     * @param vehicleResult
     * @return
     */
    public abstract VehiclePriceOutputDto matchVehicleResult(VehiclePriceOutputDto[] vehicleResult);
}

/**
 * 上海沪牌的匹配器
 */
class ShangHaiLicenseMatcher extends VehicleQueryResultMatcher {
    protected ShangHaiLicenseMatcher(VehicleInfo vehicleInfo) {
        super(vehicleInfo);
    }

    @Override
    public VehiclePriceOutputDto matchVehicleResult(VehiclePriceOutputDto[] vehicleResult) {
        if (vehicleResult.length == 1) {// 就一条记录直接返回
            return vehicleResult[0];
        }
        return getUserSelectVehicleResult(vehicleResult);// 或者返回用户选择的
    }
}

/**
 * 除了上海沪牌之外其他的匹配器
 */
class OtherMatcher extends VehicleQueryResultMatcher {
    protected OtherMatcher(VehicleInfo vehicleInfo) {
        super(vehicleInfo);
    }

    @Override
    public VehiclePriceOutputDto matchVehicleResult(VehiclePriceOutputDto[] vehicleResult) {
        String jingyouCode = getVehicleInfoExt().getJingyouCode();
        Assert.notNull(jingyouCode);

        for (VehiclePriceOutputDto vehiclePrice : vehicleResult) {
            if (vehiclePrice.getRefCode2().equals(jingyouCode)) {
                return vehiclePrice; // 返回和精友code匹配的数据
            }
        }
        return getUserSelectVehicleResult(vehicleResult); // 或者返回用户选择数据
    }
}
