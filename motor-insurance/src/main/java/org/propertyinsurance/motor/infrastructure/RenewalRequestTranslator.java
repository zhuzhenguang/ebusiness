package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.RequestBodyDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.renewquery.RequestDto;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.RenewalSpecification;

/**
 * 是否续保用户的请求
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午6:35
 */
public class RenewalRequestTranslator implements Translator {
    private RenewalSpecification specification;
    private RequestDto requestDto;


    public RenewalRequestTranslator(RenewalSpecification specification) {
        this.specification = specification;
    }

    @Override
    public void translate() throws BusinessException {
        RequestBodyDto requestBodyDto = DozerUtil.map(specification, RequestBodyDto.class);
        RequestHeadDto requestHeadDto = RequestHead.createDto(RequestType.renewalQuery, specification.getArea());
        this.requestDto = new RequestDto(requestBodyDto, requestHeadDto);
    }

    public RequestDto getRequestDto() {
        return requestDto;
    }
}
