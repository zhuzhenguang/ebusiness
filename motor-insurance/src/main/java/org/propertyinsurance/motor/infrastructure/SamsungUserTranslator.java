package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.business.SamsungUserSpecification;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryInputDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryRequest;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryRequestBody;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryResponse;

/**
 * User: zhenguang.zhu
 * Date: 13-1-9
 * Time: 上午11:01
 */
public class SamsungUserTranslator extends AbstractTranslator
        <UserQueryRequest,
                UserQueryResponse,
                SamsungUserSpecification,
                String> {
    @Override
    public UserQueryRequest translateFromSpecificationToRequest(SamsungUserSpecification specification) {
        UserQueryInputDto userQueryInputDto = DozerUtil.map(specification, UserQueryInputDto.class);
        UserQueryRequestBody userQueryRequestBody = new UserQueryRequestBody(userQueryInputDto);
        RequestHeadDto requestHeadDto = RequestHead.createDto(RequestType.isSamsungUser, specification.getArea());
        return new UserQueryRequest(userQueryRequestBody, requestHeadDto);
    }

    @Override
    public String translateFromResponseToObject(UserQueryResponse response, SamsungUserSpecification specification)
            throws BusinessException {
        checkResponseError(response.getHead());
        return response.getBody().getIdentifyNumber();
    }
}
