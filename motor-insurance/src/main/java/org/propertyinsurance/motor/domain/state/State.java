package org.propertyinsurance.motor.domain.state;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.Errors;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.PolicyQueryType;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.*;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQueryResultMatcher;

import java.util.Date;

/**
 * 业务种类
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 下午6:19
 */
public abstract class State {
    private static final String CLASS_STRING = "State";
    private static final String BASE_PACKAGE = "org.propertyinsurance.motor.domain.state.";

    private Value value;
    private BusinessService businessService;

    public State(Value value) {
        this.value = value;
        this.businessService = new BusinessServiceImpl();
    }

    /**
     * 根据类型创建一个状态
     *
     * @param value
     * @return
     */
    public static State create(Value value) {
        try {
            Class<?> areaClass = getStateRelateClass(value, "", CLASS_STRING);
            return (State) ConstructorAccess.get(areaClass).newInstance(value);
        } catch (ClassNotFoundException e) {
            throw BusinessException.create(Errors.AERA_ERROR);
        }
    }

    /**
     * 创建和地区有关的Class，用于反射
     *
     * @param stateValue
     * @param prefix
     * @param suffix
     * @return
     * @throws ClassNotFoundException
     */
    public static Class<?> getStateRelateClass(Value stateValue, String prefix, String suffix)
            throws ClassNotFoundException {
        return Class.forName(BASE_PACKAGE + prefix + stateValue.getCode() + suffix);
    }

    /**
     * 获得一个车型查询结果匹配器
     *
     * @param vehicleInfo
     * @return
     * @throws BusinessException
     */
    public VehicleQueryResultMatcher getVehicleMatcher(VehicleInfo vehicleInfo) throws BusinessException {
        return VehicleQueryResultMatcher.create(vehicleInfo, getVehicleMatcherKeyword());
    }

    /**
     * 获得车辆匹配关键字
     *
     * @return
     */
    protected abstract String getVehicleMatcherKeyword();

    /**
     * 查询用户基本信息
     *
     * @param vehicleInfo
     * @return
     */
    public abstract CommonInfo queryBasicInfo(VehicleInfo vehicleInfo) throws BusinessException;

    /**
     * 查询用户车辆信息
     *
     * @param vehicleInfo
     * @return
     */
    public abstract CommonInfo queryVehicleInfo(VehicleInfo vehicleInfo) throws BusinessException;

    /*
     * 检查保险期间
     */
    protected boolean checkPeriod(CommonInfo commonInfo) throws BusinessException {
        return commonInfo.getInsuranceInfo().checkLeadTime();
    }

    /*
     * 查看是否三星用户
     */
    protected String checkUser(CommonInfo commonInfo) throws BusinessException {
        final String licenseNo = commonInfo.getVehicleInfo().getLicenseNo();
        final String userName = commonInfo.getVehicleInfo().getCarOwner();
        final String frameNo = commonInfo.getVehicleInfo().getPropertyInfo().getFrameNo();
        SamsungUserSpecification specification = new SamsungUserSpecification(licenseNo, userName, frameNo);
        return getBusinessService().queryUserInfo(specification);
    }

    /*
     * 查询上年投保记录
     */
    protected CommonInfo queryLastPolicy(String insuredName, String identityNo) throws BusinessException {
        LastPolicySpecification specificationByIdentityNo = LastPolicySpecification.createByNameAndIdentifyNo(
                insuredName, identityNo, PolicyQueryType.QUERY_LAST_POLICYNO_BY_IDENTIFYNO);
        CommonInfo commonInfo = getBusinessService().queryLastPolicyInfo(specificationByIdentityNo);
        String lastPolicyNo = commonInfo.getInsuranceInfo().getLastYearPolicyNo();

        LastPolicySpecification specificationByPolicyNo = LastPolicySpecification.createByPolicyNo(
                lastPolicyNo, PolicyQueryType.QUERY_BY_POLICYNO);
        return getBusinessService().queryLastPolicyInfo(specificationByPolicyNo);
    }

    /*
     * 检查是否续保
     */
    protected boolean checkRenewal(CommonInfo commonInfo) throws BusinessException {
        final String carOwner = commonInfo.getVehicleInfo().getCarOwner();
        final String frameNo = commonInfo.getVehicleInfo().getPropertyInfo().getFrameNo();
        final String engineNo = commonInfo.getVehicleInfo().getPropertyInfo().getEngineNo();
        final Date commercialStartDate = commonInfo.getInsuranceInfo().getInsurancePeriod().
                getCommercialStartDate();
        final Date commercialEndDate = commonInfo.getInsuranceInfo().getInsurancePeriod().
                getCommercialEndDate();
        RenewalSpecification specification = new RenewalSpecification(carOwner, frameNo, engineNo,
                DateUtil.convertDateToString(commercialStartDate),
                DateUtil.convertDateToString(commercialEndDate));
        return getBusinessService().checkRenewal(specification);
    }

    /**
     * 获得状态枚举
     *
     * @return
     */
    public Value getValue() {
        return value;
    }

    protected BusinessService getBusinessService() {
        return businessService;
    }

    public static enum Value {
        ShangHaiLicense("1"),        // 上海沪牌
        ShangHaiNonLicense("2"),     // 上海非沪牌
        ShangHaiNew("3"),            // 上海新车
        SuZhouLicense("4"),          // 苏州牌照
        SuZhouNew("5");               // 苏州新车

        private String code;

        private Value(String code) {
            this.code = code;
        }

        public String getCode() {
            return code;
        }
    }
}
