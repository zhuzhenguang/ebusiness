package org.propertyinsurance.motor.domain.business;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.PolicyDataFlag;
import org.propertyinsurance.motor.domain.PolicyQueryType;
import org.propertyinsurance.motor.infrastructure.LastPolicyTranslator;

/**
 * 上年度保单查询条件
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 上午9:54
 */
public class LastPolicySpecification extends Specification {
    private String insuredName;
    private String identifyNumber;
    private PolicyDataFlag policyDataFlag;
    private PolicyQueryType queryType;
    private String policyNo;
    private QueryType type;

    private LastPolicySpecification(String insuredName,
                                    String identifyNumber,
                                    PolicyDataFlag policyDataFlag,
                                    PolicyQueryType queryType) {
        this.insuredName = insuredName;
        this.identifyNumber = identifyNumber;
        this.policyDataFlag = policyDataFlag;
        this.queryType = queryType;
        this.type = QueryType.ByNameAndIdentifyNo;
    }

    private LastPolicySpecification(String policyNo,
                                    PolicyDataFlag policyDataFlag,
                                    PolicyQueryType queryType) {
        this.policyDataFlag = policyDataFlag;
        this.queryType = queryType;
        this.policyNo = policyNo;
        this.type = QueryType.ByPolicyNo;
    }

    /**
     * 根据人名、证件号码生成保单查询条件
     *
     * @param insuredName
     * @param identifyNumber
     * @param queryType
     * @return
     */
    public static LastPolicySpecification createByNameAndIdentifyNo(String insuredName,
                                                                    String identifyNumber,
                                                                    PolicyQueryType queryType) {
        return new LastPolicySpecification(insuredName, identifyNumber,
                PolicyDataFlag.ALL_POLICY, queryType);
    }

    /**
     * 根据保单号生成保单查询条件
     *
     * @param policyNo
     * @param queryType
     * @return
     */
    public static LastPolicySpecification createByPolicyNo(String policyNo,
                                                           PolicyQueryType queryType) {
        return new LastPolicySpecification(policyNo, PolicyDataFlag.ALL_POLICY, queryType);
    }

    /**
     * 根据自身的保单查询类型获得一个转换器类
     *
     * @return
     * @throws BusinessException
     */
    public Class<?> getTranslator() throws BusinessException {
        try {
            return Class.forName(LastPolicyTranslator.PREFIX +
                    getType().getValue() +
                    LastPolicyTranslator.SUFFIX);
        } catch (ClassNotFoundException e) {
            throw new BusinessException("保单查询类型错误");
        }
    }

    public String getInsuredName() {
        return insuredName;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public PolicyDataFlag getPolicyDataFlag() {
        return policyDataFlag;
    }

    public PolicyQueryType getQueryType() {
        return queryType;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    private QueryType getType() {
        return type;
    }

    /**
     * 通过姓名和证件号码查询，还是根据保单号查询
     */
    public static enum QueryType {
        ByNameAndIdentifyNo("ByNameAndIdentifyNo"), ByPolicyNo("ByPolicyNo");

        private String value;

        private QueryType(String value) {
            this.value = value;
        }

        public String getValue() {
            return value;
        }
    }
}
