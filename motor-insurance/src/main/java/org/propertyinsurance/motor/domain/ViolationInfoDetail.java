package org.propertyinsurance.motor.domain;

import java.util.Date;

/**
 * 违章详细信息
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:30
 */
public class ViolationInfoDetail {
    private Date violationTime;           // 违章时间
    private Date acceptDate;              // 受理时间
    private String violationAddress;     // 违章地点
    private String violationAction;      // 违章行为代码
    private double coefficent;           // 调整系数
    private String violationType;         // 违章行为分类
    private String identifyType;          // 违章驾驶员证件类型
    private String identifyNumber;        // 违章驾驶员证件号码
    private String violationActionDesc;  // 违章行为描述
    private String violationTypeDesc;    // 违章行为分类描述
    private String trafficFlag;           // 交强险浮动标志
    private String breakSort;             // 违章种类

    public Date getViolationTime() {
        return violationTime;
    }

    public void setViolationTime(Date violationTime) {
        this.violationTime = violationTime;
    }

    public Date getAcceptDate() {
        return acceptDate;
    }

    public void setAcceptDate(Date acceptDate) {
        this.acceptDate = acceptDate;
    }

    public String getViolationAddress() {
        return violationAddress;
    }

    public void setViolationAddress(String violationAddress) {
        this.violationAddress = violationAddress;
    }

    public String getViolationAction() {
        return violationAction;
    }

    public void setViolationAction(String violationAction) {
        this.violationAction = violationAction;
    }

    public double getCoefficent() {
        return coefficent;
    }

    public void setCoefficent(double coefficent) {
        this.coefficent = coefficent;
    }

    public String getViolationType() {
        return violationType;
    }

    public void setViolationType(String violationType) {
        this.violationType = violationType;
    }

    public String getIdentifyType() {
        return identifyType;
    }

    public void setIdentifyType(String identifyType) {
        this.identifyType = identifyType;
    }

    public String getIdentifyNumber() {
        return identifyNumber;
    }

    public void setIdentifyNumber(String identifyNumber) {
        this.identifyNumber = identifyNumber;
    }

    public String getViolationActionDesc() {
        return violationActionDesc;
    }

    public void setViolationActionDesc(String violationActionDesc) {
        this.violationActionDesc = violationActionDesc;
    }

    public String getViolationTypeDesc() {
        return violationTypeDesc;
    }

    public void setViolationTypeDesc(String violationTypeDesc) {
        this.violationTypeDesc = violationTypeDesc;
    }

    public String getTrafficFlag() {
        return trafficFlag;
    }

    public void setTrafficFlag(String trafficFlag) {
        this.trafficFlag = trafficFlag;
    }

    public String getBreakSort() {
        return breakSort;
    }

    public void setBreakSort(String breakSort) {
        this.breakSort = breakSort;
    }
}
