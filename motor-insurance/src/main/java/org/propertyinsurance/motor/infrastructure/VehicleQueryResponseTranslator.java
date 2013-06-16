package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryResponse;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryResponseBody;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.VehiclePriceOutputDto;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.VehicleInfo;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQueryResultMatcher;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQuerySpecification;

/**
 * 车型查询结果翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午9:58
 */
public class VehicleQueryResponseTranslator extends ResponseTranslator {
    private CarQueryResponse response;
    private VehicleQuerySpecification specification;
    private VehicleInfo vehicleInfo;

    public VehicleQueryResponseTranslator(CarQueryResponse response, VehicleQuerySpecification specification) {
        this.response = response;
        this.specification = specification;
    }

    @Override
    public void translate() throws BusinessException {
        checkResponseError(response.getHead());
        CarQueryResponseBody body = response.getBody();
        VehicleQueryResultMatcher matcher = specification.getResultMatcher();
        VehiclePriceOutputDto vehicle = matcher.matchVehicleResult(body.getVehicle()); // 匹配器去匹配

        if (vehicle == null) {// 需要返回让用户选择
            VehicleInfo vehicleInfo = new VehicleInfo();
            vehicleInfo.getVehicleInfoExt().setVehiclePrices(body.getVehicle());
            this.vehicleInfo = vehicleInfo;
            return;
        }

        VehicleInfo vehicleInfo = DozerUtil.map(vehicle, VehicleInfo.class);
        vehicleInfo.getPriceInfo().setPriced(translatePriced(vehicle));
        this.vehicleInfo = vehicleInfo;
    }

    public VehicleInfo getVehicleInfo() {
        return vehicleInfo;
    }

    /*
     * 转换是否定价标志
     */
    private boolean translatePriced(VehiclePriceOutputDto vehicle) {
        return "1".equals(vehicle.getIsPriced());
    }
}
