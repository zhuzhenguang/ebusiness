package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.RenewalSpecification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.RequestBodyDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.RequestDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.ResponseDto;

/**
 * 续保查询转换
 *
 * User: zhenguang.zhu
 * Date: 13-1-16
 * Time: 上午12:31
 */
public class RenewalTranslator extends AbstractTranslator
        <RequestDto, ResponseDto, RenewalSpecification, Boolean> {

    @Override
    public RequestDto translateFromSpecificationToRequest(RenewalSpecification specification) {
        RequestBodyDto requestBodyDto = DozerUtil.map(specification, RequestBodyDto.class);
        RequestHeadDto requestHeadDto = RequestHead.createDto(RequestType.renewalQuery, specification.getArea());
        return new RequestDto(requestBodyDto, requestHeadDto);
    }

    @Override
    public Boolean translateFromResponseToObject(ResponseDto responseDto, RenewalSpecification specification)
            throws BusinessException {
        checkResponseError(responseDto.getHead());
        return "1".equals(responseDto.getBody().getFlag());
    }
}
