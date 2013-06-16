package org.propertyinsurance.motor.domain.state;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuerySpecification;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQuerySpecification;
import org.apache.commons.lang.StringUtils;

/**
 * 上海沪牌
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-16
 * Time: 上午11:36
 */
public class ShangHaiLicenseState extends State {
    private static final String STATE_STRING = "ShangHaiLicense";

    public ShangHaiLicenseState(Value value) {
        super(value);
    }

    @Override
    protected String getVehicleMatcherKeyword() {
        return STATE_STRING;
    }

    @Override
    public CommonInfo queryBasicInfo(VehicleInfo vehicleInfo) throws BusinessException {
        VehicleInfo vehicleQueryResult = queryVehicle(vehicleInfo);
        CommonInfo proposalQueryResult = queryProposal(vehicleQueryResult);
        if (checkPeriod(proposalQueryResult)) {// 检查保险期间
            String identityNo = checkUser(proposalQueryResult);// 检查是否三星用户
            if (StringUtils.isNotBlank(identityNo)) {
                String carOwner = proposalQueryResult.getVehicleInfo().getCarOwner();
                CommonInfo policyQueryResult = queryLastPolicy(carOwner, identityNo);// 查询上年保单记录
            }
            // checkRenewal() 检查是否续保
            proposalQueryResult.getInsuranceInfo().checkDoubleInsurance(); // 检查是否交强险重复投保
            proposalQueryResult.getInsuranceInfo().checkInsuranceExpire(); // 检查是否脱保

        }
        return proposalQueryResult;
    }

    @Override
    public CommonInfo queryVehicleInfo(VehicleInfo vehicleInfoe) throws BusinessException {
        return null; // 上海沪牌一切信息都在基本信息中
    }

    /*
     * 车型查询
     */
    private VehicleInfo queryVehicle(VehicleInfo vehicleInfo) throws BusinessException {
        VehicleQuerySpecification vehicleQuerySpecification = VehicleQuerySpecification.
                getForShangHaiLicense(vehicleInfo);
        return getBusinessService().queryVehicleInfo(vehicleQuerySpecification);
    }

    /*
     * 投保查询
     */
    private CommonInfo queryProposal(VehicleInfo vehicleInfo) throws BusinessException {
        ProposalQuerySpecification proposalQuerySpecification = ProposalQuerySpecification.
                createForShangHaiLicense(vehicleInfo);
        return getBusinessService().queryProposalInfo(proposalQuerySpecification);
    }

}
