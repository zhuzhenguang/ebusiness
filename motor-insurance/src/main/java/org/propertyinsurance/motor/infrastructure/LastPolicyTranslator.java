package org.propertyinsurance.motor.infrastructure;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.*;
import org.propertyinsurance.motor.domain.business.LastPolicySpecification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.*;

/**
 * 上年度保单查询转换
 * 系统中现在分为按照姓名和证件号查询，会获得多个保单
 * 以及根据保单号查询，会获得一个保单
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 上午10:36
 */
public abstract class LastPolicyTranslator extends AbstractTranslator
        <RequestDto, ResponseDto, LastPolicySpecification, CommonInfo> {
    public static final String PREFIX = "LastPolicy";
    public static final String SUFFIX = "Translator";

    /**
     * 动态创建子类对象
     *
     * @param translatorClazz
     * @return
     */
    public static LastPolicyTranslator create(Class<?> translatorClazz) {
        ConstructorAccess<?> access = ConstructorAccess.get(translatorClazz);
        return (LastPolicyTranslator) access.newInstance();
    }

    @Override
    public RequestDto translateFromSpecificationToRequest(LastPolicySpecification specification) {
        RequestBodyDto requestBodyDto = DozerUtil.map(specification, RequestBodyDto.class);
        requestBodyDto.setPolicyDataFlag(specification.getPolicyDataFlag().getValue());
        requestBodyDto.setQueryType(specification.getQueryType().getValue());
        RequestHeadDto requestHeadDto = RequestHead.createDto(RequestType.queryLastPolicy, specification.getArea());
        return new RequestDto(requestBodyDto, requestHeadDto);
    }

    @Override
    public abstract CommonInfo translateFromResponseToObject(ResponseDto response,
                                                             LastPolicySpecification specification)
            throws BusinessException;
}

/**
 * 根据身份证件获得的保单
 */
class LastPolicyByNameAndIdentifyNoTranslator extends LastPolicyTranslator {
    @Override
    public CommonInfo translateFromResponseToObject(ResponseDto response,
                                                    LastPolicySpecification specification)
            throws BusinessException {
        return getByNameAndIdentifyNo(response.getResponseBodyDto().getPolicyData()[0]);
    }

    /*
     * 根据姓名和证件号返回的结果
     */
    private CommonInfo getByNameAndIdentifyNo(PolicyDataDto policyDataDto) {
        PrpCitemCarDto prpCitemCarDto = policyDataDto.getPrpCitemCarDto();
        PrpCmainDto prpCmainDto = policyDataDto.getPrpCmainDto();
        VehicleInfo vehicleInfo = DozerUtil.map(prpCitemCarDto, VehicleInfo.class);
        InsuranceInfo insuranceInfo = DozerUtil.map(prpCmainDto, InsuranceInfo.class);
        return CommonInfo.createWithVehicleAndInsurance(vehicleInfo, insuranceInfo);
    }

}

/**
 * 根据保单号获得的保单
 */
class LastPolicyByPolicyNoTranslator extends LastPolicyTranslator {
    @Override
    public CommonInfo translateFromResponseToObject(ResponseDto response,
                                                    LastPolicySpecification specification)
            throws BusinessException {
        checkResponseError(response.getResponseHeadDto());
        return getByPolicyNo(response.getResponseBodyDto().getPolicyData()[0]);
    }

    /*
     * 根据保单号返回结果
     */
    private CommonInfo getByPolicyNo(PolicyDataDto policyDataDto) {
        PrpCinsuredDto[] prpCinsuredDtos = policyDataDto.getPrpCinsuredDto();
        PrpCitemCarDto prpCitemCarDto = policyDataDto.getPrpCitemCarDto();
        PrpCitemKindDto[] prpCitemKindDtos = policyDataDto.getPrpCitemKindDto();
        PrpCcarDriverDto[] prpCcarDriverDtos = policyDataDto.getPrpCcarDriverDto();

        return CommonInfo.createWithVehicleInsuranceAndPremium(getVehicle(prpCitemCarDto),
                getInsurace(prpCinsuredDtos, prpCcarDriverDtos), getPremium(prpCitemKindDtos));
    }

    /*
     * 获得车辆信息
     */
    private VehicleInfo getVehicle(PrpCitemCarDto prpCitemCarDto) {
        return DozerUtil.map(prpCitemCarDto, VehicleInfo.class);
    }

    /*
     * 获得保费信息
     */
    private PremiumInfo getPremium(PrpCitemKindDto[] prpCitemKindDtos) {
        return PremiumInfo.createWithKinds(prpCitemKindDtos);
    }

    /*
     * 获得一般保险对象
     */
    private InsuranceInfo getInsurace(PrpCinsuredDto[] prpCinsuredDtos,
                                      PrpCcarDriverDto[] prpCcarDriverDtos) {
        InsuranceInfo insuranceInfo = new InsuranceInfo();
        insuranceInfo.setDriverSet(prpCcarDriverDtos);
        insuranceInfo.setInsuranceRelatePersons(prpCinsuredDtos);
        return insuranceInfo;
    }
}
