package org.propertyinsurance.motor.infrastructure;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.userquery.UserQueryResponse;
import org.propertyinsurance.motor.BusinessException;

/**
 * 用户查询结果翻译
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午9:45
 */
public class UserResponseTranslator extends ResponseTranslator {
    private UserQueryResponse response;
    private String identityNo;

    public UserResponseTranslator(UserQueryResponse response) {
        this.response = response;
    }

    @Override
    public void translate() throws BusinessException {
        checkResponseError(response.getHead());
        this.identityNo = response.getBody().getIdentifyNumber();
    }

    public String getIdentityNo() {
        return identityNo;
    }
}
