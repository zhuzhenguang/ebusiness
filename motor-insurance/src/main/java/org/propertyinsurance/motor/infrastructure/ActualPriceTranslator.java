package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.ActualPriceSpecification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueRequestBody;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueResponse;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;

/**
 * 实际价格计算.
 * User: zhenguang.zhu
 * Date: 12-12-23
 * Time: 下午6:13
 */
public class ActualPriceTranslator extends AbstractTranslator
        <ActualValueRequest, ActualValueResponse, ActualPriceSpecification, Double>
        implements Translator{

    @Override
    public ActualValueRequest translateFromSpecificationToRequest(ActualPriceSpecification specification) {
        RequestHeadDto head = RequestHead.createDto(RequestType.actualValueCalculate, specification.getArea());
        ActualValueRequestBody body = DozerUtil.map(specification, ActualValueRequestBody.class);
        return new ActualValueRequest(body, head);
    }

    @Override
    public Double translateFromResponseToObject(ActualValueResponse response,
                                                   ActualPriceSpecification specification) throws BusinessException {
        checkResponseError(response.getHead());
        return response.getBody().getActualValue();
    }

    @Override
    public void translate() {
        //To change body of implemented methods use File | Settings | File Templates.
    }
}
