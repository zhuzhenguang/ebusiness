package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.ResponseDto;
import org.propertyinsurance.motor.BusinessException;

/**
 * 是否续保的结果翻译
 * User: zhu
 * Date: 13-2-14
 * Time: 下午9:38
 */
public class RenewalResponseTranslator extends ResponseTranslator {
    private ResponseDto response;
    private boolean renewal;

    public RenewalResponseTranslator(ResponseDto response) {
        this.response = response;
    }

    @Override
    public void translate() throws BusinessException {
        checkResponseError(response.getHead());
        this.renewal = "1".equals(response.getBody().getFlag());
    }

    public boolean isRenewal() {
        return renewal;
    }
}
