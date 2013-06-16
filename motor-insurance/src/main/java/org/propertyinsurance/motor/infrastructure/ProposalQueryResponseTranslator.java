package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.*;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.*;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuerySpecification;

/**
 * 投保查询结果翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午6:26
 */
public class ProposalQueryResponseTranslator extends ResponseTranslator {
    private TBQueryResponse response;
    private ProposalQuerySpecification specification;
    private CommonInfo commonInfo;

    public ProposalQueryResponseTranslator(TBQueryResponse response, ProposalQuerySpecification specification) {
        this.response = response;
        this.specification = specification;
    }

    @Override
    public void translate() throws BusinessException {
        checkResponseError(response.getHead());
        TBQueryCarOutputDto carOutputDto = response.getBody().getCar();
        TBQueryVehiclePriceDto vehiclePriceDto = getVehiclePrice(response.getBody().getVehiclePrice(), specification);
        TBQueryBiClaimOutDto[] claimOutDtos = response.getBody().getClaim();
        TBQueryPeccDto[] peccDtos = response.getBody().getPecc();

        VehicleInfo vehicleInfo = mapCar(carOutputDto, vehiclePriceDto);
        InsuranceInfo insuranceInfo = mapInsurance(carOutputDto, peccDtos, claimOutDtos);
        this.commonInfo = CommonInfo.createWithVehicleAndInsurance(vehicleInfo, insuranceInfo);
    }

    public CommonInfo getCommonInfo() {
        return commonInfo;
    }

    /*
     * 获得唯一车价信息
     */
    private TBQueryVehiclePriceDto getVehiclePrice(TBQueryVehiclePriceDto[] vehiclePriceDtos,
                                                   ProposalQuerySpecification specification) {
        int selectedNo = specification.getSelectedNo();
        return selectedNo > 0 ? vehiclePriceDtos[selectedNo - 1] : vehiclePriceDtos[0];
    }

    /*
     * 转换车辆信息
     */
    private VehicleInfo mapCar(TBQueryCarOutputDto carOutputDto, TBQueryVehiclePriceDto vehiclePriceDto) {
        VehicleInfo vehicleInfo = DozerUtil.map(carOutputDto, VehicleInfo.class);// 转换车辆信息
        vehicleInfo.getPhysicalInfo().setVehicleColor(VehicleColor.get(
                carOutputDto.getLicenseColorCode()));// 转换车身颜色
        DozerUtil.map(vehiclePriceDto, vehicleInfo); // 转换车架信息
        return vehicleInfo;
    }

    /*
     * 转换保险信息
     */
    private InsuranceInfo mapInsurance(TBQueryCarOutputDto carOutputDto,
                                       TBQueryPeccDto[] peccDtos,
                                       TBQueryBiClaimOutDto[] claimOutDtos) {
        InsuranceInfo insuranceInfo = DozerUtil.map(carOutputDto, InsuranceInfo.class);
        mapViolationInfo(peccDtos, insuranceInfo);
        mapClaimInfo(claimOutDtos, insuranceInfo);
        return insuranceInfo;
    }

    /*
     * 转换理赔信息
     */
    private void mapClaimInfo(TBQueryBiClaimOutDto[] claimOutDtos, InsuranceInfo insuranceInfo) {
        for (TBQueryBiClaimOutDto claimOutDto : claimOutDtos) {
            ClaimInfoDetail detail = DozerUtil.map(claimOutDto, ClaimInfoDetail.class);
            insuranceInfo.addClaimDetail(detail);
        }
    }

    /*
     * 转换违章信息
     */
    private void mapViolationInfo(TBQueryPeccDto[] peccDtos, InsuranceInfo insuranceInfo) {
        for (TBQueryPeccDto peccDto : peccDtos) {
            ViolationInfoDetail detail = DozerUtil.map(peccDto, ViolationInfoDetail.class);
            insuranceInfo.addViolationDetail(detail);
        }
    }
}
