package org.propertyinsurance.motor.domain.business;

import org.apache.commons.lang.StringUtils;

/**
 * User: zhenguang.zhu
 * Date: 13-1-9
 * Time: 上午10:55
 */
public class SamsungUserSpecification extends Specification {
    private String licenseNo;
    private String userName;
    private String frameNo;

    public SamsungUserSpecification(String licenseNo, String userName, String frameNo) {
        this.licenseNo = StringUtils.defaultString(licenseNo);
        this.userName = userName;
        this.frameNo = StringUtils.defaultString(frameNo);
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public String getUserName() {
        return userName;
    }

    public String getFrameNo() {
        return frameNo;
    }
}
