package org.propertyinsurance.motor.infrastructure;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.*;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.CommonInfo;
import org.propertyinsurance.motor.domain.InsuranceInfo;
import org.propertyinsurance.motor.domain.PremiumInfo;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.LastPolicySpecification;

/**
 * 上年度保单查询结果翻译
 *
 * User: zhu
 * Date: 13-2-14
 * Time: 下午6:03
 */
public abstract class LastPolicyResponseTranslator extends ResponseTranslator {
    public static final String PREFIX = "LastPolicy";
    public static final String SUFFIX = "Translator";

    private ResponseDto responseDto;
    private CommonInfo commonInfo;

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

    public ResponseDto getResponseDto() {
        return responseDto;
    }

    protected void setResponseDto(ResponseDto responseDto) {
        this.responseDto = responseDto;
    }

    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    protected void setCommonInfo(CommonInfo commonInfo) {
        this.commonInfo = commonInfo;
    }
}

/**
 * 根据身份证件获得的保单
 */
class LastPolicyResponseByNameAndIdentifyNoTranslator extends LastPolicyResponseTranslator {
    LastPolicyResponseByNameAndIdentifyNoTranslator(ResponseDto responseDto) {
        setResponseDto(responseDto);
    }

    @Override
    public void translate() throws BusinessException {
        PolicyDataDto policyDataDto = getResponseDto().getResponseBodyDto().getPolicyData()[0];
        PrpCitemCarDto prpCitemCarDto = policyDataDto.getPrpCitemCarDto();
        PrpCmainDto prpCmainDto = policyDataDto.getPrpCmainDto();
        VehicleInfo vehicleInfo = DozerUtil.map(prpCitemCarDto, VehicleInfo.class);
        InsuranceInfo insuranceInfo = DozerUtil.map(prpCmainDto, InsuranceInfo.class);
        setCommonInfo(CommonInfo.createWithVehicleAndInsurance(vehicleInfo, insuranceInfo));
    }
}

/**
 * 根据保单号获得的保单
 */
class LastPolicyResponseByPolicyNoTranslator extends LastPolicyResponseTranslator {
    @Override
    public void translate() throws BusinessException {
        checkResponseError(getResponseDto().getResponseHeadDto());
        setCommonInfo(getByPolicyNo(getResponseDto().getResponseBodyDto().getPolicyData()[0]));
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

