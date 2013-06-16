package org.propertyinsurance.motor;

/**
 * 常量类.
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-12
 * Time: 上午9:13
 */
public class PropertyConstant {
    private PropertyConstant() {
    }

    /**
     * 邮件发送参数
     */
    public static String URl = "http://109.101.102.110:7012/messageweb/interface";// email&&sms核心地址
    public static String smsTime = "30";// 设置短信验证码的有效时间
    public static String smsValidatetime = "60";// 设置验证码有效时间
    public static String count = "5";// 设置验证码有效时间

    /**
     * 投保单保存参数
     */
    //上海地区
    public static String MAKECOM = "06320017";// 出单机构
    public static String COMCODE = "06320017";// 业务归属机构代码
    public static String HANDLERCODE = "90632003";// 经办人代码
    public static String HANDLER1CODE = "90632003";//归属业务员代码
    public static String OPERATORCODE = "90632002";//操作员代码第一次录入人员代码
    public static String userCode = "1010040";
    public static String passWord = "0000";
    public static String logonComCode = "06320000";
    public static String riskCode = "0502";
    public static String systemCode = "01";

    //苏州地区
    public static String szMakecom = "06360004";// 出单机构
    public static String szComcode = "06360004";// 业务归属机构代码
    public static String szHandlercode = "90636003";// 经办人代码
    public static String szHandler1code = "90636003";//归属业务员代码
    public static String szOperatorcode = "90636002";//操作员代码第一次录入人员代码
    public static String szUserCode = "13000130";
    public static String szPassWord = "0000";
    public static String szLogonComCode = "06360000";

    public static String salesChannelCode = "PISUNG0480"; // 渠道代码（上海）
    public static String salesChannelCodes = "PISUNG0480";// 渠道代码（苏州）
    public static String vinno = "12345678901112134";
    public static String engineno = "1234567";
    public static String licenseKindCode = "02";          // 号牌种类
    public static String carKindCode = "A0";               // 车辆种类
    public static String payMethod = "07";                 // 支付方式（快钱）
    public static String defaultVehicleStyleDesc = "小型轿车";
    public static String useNatureCode = "8A";             // 使用性质
    public static String B_Kind = "B";                      // B险险别，第三者责任险
    public static String breakRuleDesc = "机动车未按规定鸣喇叭示意的/驾驶人未按规定使用安全带的/机动车违反规定停放、临时停车且驾驶人不在现场或驾驶人虽在现场拒绝立即驶离，妨碍其它车辆、行人通行的";
    public static String clausetype = "F46";                // 条款类别，默认自由组合
}
