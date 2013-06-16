package org.propertyinsurance.motor.domain;

/**
 * 保险关系人对象
 *
 * User: zhenguang.zhu
 * Date: 12-8-30
 * Time: 下午4:44
 */
public class InsuredRelationPerson {
    private String insuredName;    // 保险关系人姓名
    private String identifyType;   // 证件类型
    private String identifyNumber; // 证件号
    private String mobile;          // 手机号
    private String email;           // 邮箱
    private String insuredAddress; // 地址
    private String postcode;        // 邮编
    private InsuredType insuredType; // 类型，保险人或被保险人

    /**
     * 转换保险人类型
     *
     * @param insuredFlag
     */
    public void setInsuredType(String insuredFlag) {
        setInsuredType(InsuredType.get(insuredFlag));
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getInsuredAddress() {
        return insuredAddress;
    }

    public void setInsuredAddress(String insuredAddress) {
        this.insuredAddress = insuredAddress;
    }

    public String getPostcode() {
        return postcode;
    }

    public void setPostcode(String postcode) {
        this.postcode = postcode;
    }

    public InsuredType getInsuredType() {
        return insuredType;
    }

    public void setInsuredType(InsuredType insuredType) {
        this.insuredType = insuredType;
    }
}
