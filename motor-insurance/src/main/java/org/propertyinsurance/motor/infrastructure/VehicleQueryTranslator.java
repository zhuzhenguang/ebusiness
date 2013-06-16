package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQueryResultMatcher;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQuerySpecification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.*;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;

/**
 * 车型查询转换器.
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-13
 * Time: 下午3:04
 */
public class VehicleQueryTranslator extends AbstractTranslator
        <CarQueryRequest, CarQueryResponse, VehicleQuerySpecification, VehicleInfo> {

    @Override
    public CarQueryRequest translateFromSpecificationToRequest(VehicleQuerySpecification specification) {
        RequestHeadDto head = RequestHead.createDto(RequestType.carQuery, specification.getArea());

        CarQueryInputDto carQueryInputDto = DozerUtil.map(specification, CarQueryInputDto.class);
        carQueryInputDto.setEcdemicVehicleFlag(translateIsNotShangHaiLicense(specification));
        carQueryInputDto.setNewVehicleFlag(translateNewCar(specification));

        CarQueryRequestBody body = new CarQueryRequestBody(carQueryInputDto);
        return new CarQueryRequest(body, head);
    }

    @Override
    public VehicleInfo translateFromResponseToObject(CarQueryResponse response,
                                                     VehicleQuerySpecification specification) {
        checkResponseError(response.getHead());
        CarQueryResponseBody body = response.getBody();
        VehicleQueryResultMatcher matcher = specification.getResultMatcher();
        VehiclePriceOutputDto vehicle = matcher.matchVehicleResult(body.getVehicle()); // 匹配器去匹配

        if (vehicle == null) {// 需要返回让用户选择
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.getVehicleInfoExt().setVehiclePrices(body.getVehicle());
            return vehicleInfo;
        }

        VehicleInfo vehicleInfo = DozerUtil.map(vehicle, VehicleInfo.class);
        vehicleInfo.getPriceInfo().setPriced(translatePriced(vehicle));
        return vehicleInfo;
    }

    /*
     * 转换外地车标志
     */
    private String translateIsNotShangHaiLicense(VehicleQuerySpecification specification) {
        return specification.isNotShangHaiLicense() ? "1" : "0";
    }

    /*
     * 转换新车标志
     */
    private String translateNewCar(VehicleQuerySpecification specification) {
        return specification.isNewCar() ? "1" : "0";
    }

    /*
     * 转换是否定价标志
     */
    private boolean translatePriced(VehiclePriceOutputDto vehicle) {
        return "1".equals(vehicle.getIsPriced());
    }

}
