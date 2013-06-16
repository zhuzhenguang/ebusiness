package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryInputDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.CarQueryRequestBody;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.vehicle.VehicleQuerySpecification;

/**
 * 车型查询请求翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午9:51
 */
public class VehicleQueryRequestTranslator implements Translator {
    private VehicleQuerySpecification specification;
    private CarQueryRequest request;

    public VehicleQueryRequestTranslator(VehicleQuerySpecification specification) {
        this.specification = specification;
    }

    @Override
    public void translate() throws BusinessException {
        RequestHeadDto head = RequestHead.createDto(RequestType.carQuery, specification.getArea());

        CarQueryInputDto carQueryInputDto = DozerUtil.map(specification, CarQueryInputDto.class);
        carQueryInputDto.setEcdemicVehicleFlag(translateIsNotShangHaiLicense(specification));
        carQueryInputDto.setNewVehicleFlag(translateNewCar(specification));

        CarQueryRequestBody body = new CarQueryRequestBody(carQueryInputDto);
        this.request = new CarQueryRequest(body, head);
    }

    public CarQueryRequest getRequest() {
        return request;
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
}
