package org.propertyinsurance.motor.infrastructure;

/**
 * Created with IntelliJ IDEA.
 * User: zhenguang.zhu
 * Date: 12-12-12
 * Time: 上午8:22
 * To change this template use File | Settings | File Templates.
 */
public enum RequestType {
    //		01 车型查询
//		02 投保查询
//		03 保费计算
//		04  投保单保存
//		05 提交核保
//		06 上海缴费申请
//		07 投保确认/上海缴费成功
//		08 交易撤销
//		09 后台登录
//		10 投保单查询
//		11 保单查询
//		12投保历史查询
//		13 报案理赔历史查询
//		14新增设备计算
//		15 判断是否三星客户
//		16车船税计算
//		17查询用户最近一次保单信息

    /**
     * 接口查询类型   01车型查询
     */
    carQuery("01", 1),
    /**
     * 接口查询类型   02投保查询
     */
    tbQuery("02", 2),
    /**
     * 接口查询类型   03保费计算
     */
    calculateFee("03", 3),
    /**
     * 接口查询类型   04 投保单保存
     */
    saveProposal("04", 4),
    /**
     * 接口查询类型   05提交核保
     */
    applyHB("05", 5),
    /**
     * 接口查询类型   06上海缴费申请
     */
    feeApply("06", 6),
    /**
     * 接口查询类型   07 投保确认(上海缴费成功)
     */
    tbCheck("07", 7),
    /**
     * 接口查询类型   08 交易撤销
     */
    cancelDeal("08", 8),
    /**
     * 接口查询类型   09 后台登录
     */
    loginBack("09", 9),
    /**
     * 接口查询类型   10 投保单查询
     */
    temperayQuery("10", 10),
    /**
     * 接口查询类型   11 保单查询
     */
    proposalQuery("11", 11),
    /**
     * 接口查询类型   12投保历史查询
     */
    insuranceQuery("12", 12),
    /**
     * 接口查询类型   13 报案理赔历史查询
     */
    claimQuery("13", 13),
    /**
     * 接口查询类型   14 新增设备计算
     */
    carDeviceCalculate("14", 14),
    /**
     * 接口查询类型   15 判断是否三星客户
     */
    isSamsungUser("15", 15),
    /**
     * 接口查询类型   16 车船税计算
     */
    carTaxShipCalculate("16", 16),
    /**
     * 接口查询类型   17 查询用户最近一次保单信息
     */
    queryLastPolicy("17", 17),
    /**
     * 接口查询类型   18 全国投保确认
     */
    QGTBPayConfirm("18", 18),
    /**
     * 接口查询类型   19 车辆实际价值计算
     */
    actualValueCalculate("19", 19),
    /**
     * 接口查询类型   19 车辆实际价值计算
     */
    userQuery("20", 20),
    /**
     * 接口查询类型   21 续保查询
     */
    renewalQuery("21", 21);

    private String value;

    private int key;

    public int getKey() {
        return key;
    }

    public String getValue() {
        return this.value;
    }

    private RequestType(String value, int key) {
        this.value = value;
        this.key = key;
    }
}
