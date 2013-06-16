package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.caculate.actualvalue.ActualValueResponse;

/**
 * 翻译实际价格返回结果
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午5:49
 */
public class ActualPriceResponseTranslator extends ResponseTranslator {
    private ActualValueResponse response;
    private Double actualPrice;

    public ActualPriceResponseTranslator(ActualValueResponse response) {
        this.response = response;
    }

    @Override
    public void translate() {
        checkResponseError(response.getHead());
        this.actualPrice = response.getBody().getActualValue();
    }

    public Double getActualPrice() {
        return actualPrice;
    }
}
