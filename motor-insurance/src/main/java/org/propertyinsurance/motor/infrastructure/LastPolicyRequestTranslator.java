package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.RequestBodyDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.RequestDto;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.LastPolicySpecification;

/**
 * 上年度保单查询请求翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午5:59
 */
public class LastPolicyRequestTranslator implements Translator {
    private LastPolicySpecification specification;
    private RequestDto requestDto;

    public LastPolicyRequestTranslator(LastPolicySpecification specification) {
        this.specification = specification;
    }

    @Override
    public void translate() {
        RequestBodyDto requestBodyDto = DozerUtil.map(specification, RequestBodyDto.class);
        requestBodyDto.setPolicyDataFlag(specification.getPolicyDataFlag().getValue());
        requestBodyDto.setQueryType(specification.getQueryType().getValue());
        RequestHeadDto requestHeadDto = RequestHead.createDto(RequestType.queryLastPolicy, specification.getArea());
        this.requestDto = new RequestDto(requestBodyDto, requestHeadDto);
    }

    public RequestDto getRequestDto() {
        return requestDto;
    }
}
