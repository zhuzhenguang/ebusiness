package org.propertyinsurance.motor.domain.business;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuerySpecification;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQuerySpecification;

/**
 * 业务服务接口
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 下午6:23
 */
public interface BusinessService {
    /**
     * 车型查询
     *
     * @param specification 条件
     * @return 车辆信息
     * @throws BusinessException
     */
    VehicleInfo queryVehicleInfo(VehicleQuerySpecification specification) throws BusinessException;

    /**
     * 实际价格查询
     *
     * @param specification 条件
     * @return 车辆实际价格
     * @throws BusinessException
     */
    double queryActualPrice(ActualPriceSpecification specification) throws BusinessException;

    /**
     * 投保查询
     *
     * @param specification
     * @return 车辆信息和保险信息
     * @throws BusinessException
     */
    CommonInfo queryProposalInfo(ProposalQuerySpecification specification) throws BusinessException;

    /**
     * 三星用户查询
     *
     * @param specification
     * @return 如果返回值不为null，就是用户证件号码，否则就不是三星用户
     * @throws BusinessException
     */
    String queryUserInfo(SamsungUserSpecification specification) throws BusinessException;

    /**
     * 上年度保单查询，根据人名和证件号或者根据保单号
     *
     * @param specification
     * @return 车辆信息和保险信息
     * @throws BusinessException
     */
    CommonInfo queryLastPolicyInfo(LastPolicySpecification specification) throws BusinessException;

    /**
     * 续保查询
     *
     * @param specification
     * @return 是否续保用户
     * @throws BusinessException
     */
    Boolean checkRenewal(RenewalSpecification specification) throws BusinessException;

}
