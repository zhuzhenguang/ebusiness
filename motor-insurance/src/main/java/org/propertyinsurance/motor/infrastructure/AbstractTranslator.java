package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.domain.business.Specification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.ResponseHeadDto;

/**
 * 转换器基类
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-13
 * Time: 下午3:15
 */
public abstract class AbstractTranslator<Req, Res, Spec extends Specification, Result> {
    /**
     * 检查是否有业务错误
     *
     * @param responseHeadDto
     * @throws BusinessException
     */
    protected void checkResponseError(ResponseHeadDto responseHeadDto) throws BusinessException {
        if (responseHeadDto.getAppResCode().equals("0") ||
                responseHeadDto.getResponseCode().equals("0")) {
            throw new BusinessException(responseHeadDto.getAppResMessage());
        }
    }

    /**
     * 将specification转换成request
     *
     * @param spec
     * @return
     */
    public abstract Req translateFromSpecificationToRequest(Spec spec);

    /**
     * 将response对象转换成业务对象
     *
     * @param res
     * @param spec
     * @return
     */
    public abstract Result translateFromResponseToObject(Res res, Spec spec) throws BusinessException;
}
