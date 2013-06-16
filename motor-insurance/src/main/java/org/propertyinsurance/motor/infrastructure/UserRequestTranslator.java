package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryInputDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryRequestBody;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.SamsungUserSpecification;

/**
 * 是否用户查询请求翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午9:42
 */
public class UserRequestTranslator implements Translator {
    private SamsungUserSpecification specification;
    private UserQueryRequest request;

    public UserRequestTranslator(SamsungUserSpecification specification) {
        this.specification = specification;
    }

    @Override
    public void translate() throws BusinessException {
        UserQueryInputDto userQueryInputDto = DozerUtil.map(specification, UserQueryInputDto.class);
        UserQueryRequestBody userQueryRequestBody = new UserQueryRequestBody(userQueryInputDto);
        RequestHeadDto requestHeadDto = RequestHead.createDto(RequestType.isSamsungUser, specification.getArea());
        this.request = new UserQueryRequest(userQueryRequestBody, requestHeadDto);
    }

    public UserQueryRequest getRequest() {
        return request;
    }
}
