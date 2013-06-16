package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueRequestBody;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.ActualPriceSpecification;

/**
 * 翻译实际价格请求
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午5:16
 */
public class ActualPriceRequestTranslator implements Translator {
    private ActualPriceSpecification specification;
    private ActualValueRequest request;

    public ActualPriceRequestTranslator(ActualPriceSpecification specification) {
        this.specification = specification;
    }

    @Override
    public void translate() {
        RequestHeadDto head = RequestHead.createDto(RequestType.actualValueCalculate, specification.getArea());
        ActualValueRequestBody body = DozerUtil.map(specification, ActualValueRequestBody.class);
        this.request = new ActualValueRequest(body, head);
    }

    public ActualValueRequest getRequest() {
        return request;
    }
}
