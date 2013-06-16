package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.ResponseHeadDto;
import org.propertyinsurance.motor.BusinessException;

/**
 * 结果翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午5:46
 */
public abstract class ResponseTranslator implements Translator {
    /**
     * 检查是否有业务错误
     *
     * @param responseHeadDto
     * @throws org.propertyinsurance.motor.BusinessException
     *
     */
    protected void checkResponseError(ResponseHeadDto responseHeadDto) throws BusinessException {
        if (responseHeadDto.getAppResCode().equals("0") ||
                responseHeadDto.getResponseCode().equals("0")) {
            throw new BusinessException(responseHeadDto.getAppResMessage());
        }
    }
}
