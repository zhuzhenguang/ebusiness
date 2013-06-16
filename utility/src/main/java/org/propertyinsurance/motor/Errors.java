package org.propertyinsurance.motor;

/**
 * 错误代码和错误信息.
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-12
 * Time: 上午8:29
 */
public enum Errors {
    PLATFORM_ERROR("SHERROR001", "您好！非常抱歉给您带来的不便。现在因平台网络出现连接异常，所以不能正常进行保费计算，我们会尽快处理，请您耐心等待。"),
    UNDERWRITE_ERROR("SHERROR002", "非常抱歉，您的车辆无法在网上投保，如有疑问，请来电咨询，谢谢！"),
    NET_ERROR("SHERROR003", "您好！非常抱歉，由于网络延时，我们将暂时无法提供服务，请稍后再试，谢谢！"),
    OWNER_LICENSENO_ERROR("SHERROR004", "非常抱歉，您填写的车主与车牌号不匹配，请您重新录入，谢谢！"),
    OWNER_INSURED_ERROR("SHERROR005", "车主或被保险人信息不符合投保规定!"),
    VALIDA_CODE_ERROR("SHERROR006", "非常抱歉，您填写的验证码有误，请您重新录入，谢谢！"),
    INSURING_ERROR("SHERROR007", "目前您还在保,如果需要投保,请通过电话预约投保"),
    DRIVER_AGE_ERROR("SHERROR008", "驾驶员年龄必须在25岁以上,并且驾龄在一年以上"),
    MULTI_RISKS_ERROR("SHERROR009", "非常抱歉，您的选择不符合多险种投保规则！"),
    PREMIUM_ERROR("SHERROR010", "您好！页面上显示的保费和系统计算的保费不一致，请重试，谢谢！"),
    SHANGHAI_VEHICLE_ERROR("SHERROR001", "您好！非常抱歉，您选择的车辆无法获得上海市机动车辆联合信息平台的确认。请重新选择车辆信息，谢谢！"),
    SHANGHAI_APPLICANT_ERROR("SHERROR002", "您好！非常抱歉，您选择的车辆无法获得上海市机动车辆联合信息平台的确认。请重新选择车辆信息，谢谢！"),
    SUZHOU_APPLICANT_ERROR("SZERROR001", "您好！非常抱歉，您选择的车辆无法获得当地机动车信息平台的确认，请重新选择车辆信息，谢谢！"),
    EXPIRE_ERROR("SHERROR011", "脱保"),
    AERA_ERROR("AREAERROR", "地区错误");


    private String message;// 错误信息
    private String code;   // 错误代码

    private Errors(String code, String message) {
        this.message = message;
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

}
