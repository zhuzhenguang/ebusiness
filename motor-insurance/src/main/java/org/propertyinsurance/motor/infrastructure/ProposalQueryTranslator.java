package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DateUtil;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.*;
import org.propertyinsurance.motor.domain.business.proposal.ProposalQuerySpecification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.tbquery.*;

/**
 * 投保查询转换器
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-6
 * Time: 下午3:51
 */
public class ProposalQueryTranslator extends AbstractTranslator
        <TBQueryRequest,
        TBQueryResponse,
        ProposalQuerySpecification,
        CommonInfo> {
    private static final String COM_COVERAGE_CODE = "B";

    @Override
    public TBQueryRequest translateFromSpecificationToRequest(ProposalQuerySpecification specification) {
        TBQueryRequestBody body = new TBQueryRequestBody();
        TBQueryCarInputDto car = DozerUtil.map(specification, TBQueryCarInputDto.class);
        body.setCar(car);
        body.setKind(getKinds());

        return new TBQueryRequest(body, RequestHead.createDto(RequestType.proposalQuery,
                specification.getArea()));
    }

    /*
     * 获得险别信息
     */
    private TBQueryKindInputDto[] getKinds() {
        TBQueryKindInputDto kind = new TBQueryKindInputDto();
        kind.setComCoverageCode(COM_COVERAGE_CODE);
        kind.setStartDate(DateUtil.getTodayPlus2DateString());
        kind.setEndDate(DateUtil.getTodayPlus1YearString());
        return new TBQueryKindInputDto[]{kind};
    }

    @Override
    public CommonInfo translateFromResponseToObject(TBQueryResponse response,
                                                    ProposalQuerySpecification specification)
            throws BusinessException {
        checkResponseError(response.getHead());
        TBQueryCarOutputDto carOutputDto = response.getBody().getCar();
        TBQueryVehiclePriceDto vehiclePriceDto = getVehiclePrice(response.getBody().getVehiclePrice(), specification);
        TBQueryBiClaimOutDto[] claimOutDtos = response.getBody().getClaim();
        TBQueryPeccDto[] peccDtos = response.getBody().getPecc();

        VehicleInfo vehicleInfo = mapCar(carOutputDto, vehiclePriceDto);
        InsuranceInfo insuranceInfo = mapInsurance(carOutputDto, peccDtos, claimOutDtos);
        return CommonInfo.createWithVehicleAndInsurance(vehicleInfo, insuranceInfo);
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
