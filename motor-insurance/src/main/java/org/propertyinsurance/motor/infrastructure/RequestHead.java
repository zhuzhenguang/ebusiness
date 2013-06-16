package org.propertyinsurance.motor.infrastructure;

import com.esotericsoftware.reflectasm.ConstructorAccess;
import com.samsungproperty.motor.Assert;
import com.samsungproperty.motor.BusinessException;
import com.samsungproperty.motor.PropertyConstant;
import org.propertyinsurance.motor.domain.area.Area;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.common.RequestHeadDto;

/**
 * 生成核心请求的头部信息.
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-12
 * Time: 上午8:20
 */
public class RequestHead {
    private static final String CLASS_STRING = "RequestHead";

    private RequestHeadDto headDto;
    private RequestType type;

    protected RequestHead(RequestType type) {
        this.headDto = new RequestHeadDto();
        this.headDto.setRiskCode(PropertyConstant.riskCode);
        this.headDto.setRequestType(type.getValue());
        this.headDto.setSystemCode(PropertyConstant.systemCode);
    }

    private static RequestHead create(RequestType type, Area area) {
        if (area.isCorrectArea()) {
            throw new BusinessException("错误的地区");
        }
        try {
            ConstructorAccess<?> constructor = ConstructorAccess.get(getRequestHeadClass(area));
            return (RequestHead) constructor.newInstance(type);
        } catch (ClassNotFoundException e) {
            throw new BusinessException("错误的地区");
        }
    }

    private static Class<?> getRequestHeadClass(Area area) throws ClassNotFoundException {
        return Class.forName(area.getValue().getAreaCode() + CLASS_STRING);
    }

    protected RequestHeadDto getHeadDto() {
        return headDto;
    }

    public static RequestHeadDto createDto(RequestType type, Area area) {
        Assert.notNull(type, "请求类型不能为空");
        return RequestHead.create(type, area).getHeadDto();
    }

}

/**
 * 上海地区请求头部
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-8-17
 * Time: 下午1:25
 */
class ShangHaiRequestHead extends RequestHead {
    protected ShangHaiRequestHead(RequestType type) {
        super(type);
        getHeadDto().setUserCode(PropertyConstant.userCode);
        getHeadDto().setPassword(PropertyConstant.passWord);
        getHeadDto().setLogonComCode(PropertyConstant.logonComCode);
    }
}

/**
 * 苏州地区请求头部
 *
 * User: zhenguang.zhu
 * Date: 12-8-17
 * Time: 下午1:32
 */
class SuZhouRequestHead extends RequestHead {
    protected SuZhouRequestHead(RequestType type) {
        super(type);
        getHeadDto().setUserCode(PropertyConstant.szUserCode);
        getHeadDto().setPassword(PropertyConstant.szPassWord);
        getHeadDto().setLogonComCode(PropertyConstant.szLogonComCode);
    }
}
