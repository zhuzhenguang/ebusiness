package org.propertyinsurance.motor.domain;

import java.util.Date;

/**
 * 驾驶员
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 下午2:57
 */
public class Driver {
    private DriverType driverType;    // 驾驶员类别
    private String name;               // 姓名
    private String drivingLicenseNo; // 驾驶证号
    private int age;                  // 年龄
    private Sex sex;                   // 性别
    private Date acceptLicenseDate;  // 初次领证日期
    private String drivingCarType;   // 准驾车型

    public DriverType getDriverType() {
        return driverType;
    }

    public void setDriverType(DriverType driverType) {
        this.driverType = driverType;
    }

    public void setDriverType(String code) {
        setDriverType(DriverType.get(code));
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDrivingLicenseNo() {
        return drivingLicenseNo;
    }

    public void setDrivingLicenseNo(String drivingLicenseNo) {
        this.drivingLicenseNo = drivingLicenseNo;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Sex getSex() {
        return sex;
    }

    public void setSex(Sex sex) {
        this.sex = sex;
    }

    public void setSex(String code) {
        setSex(Sex.get(code));
    }

    public Date getAcceptLicenseDate() {
        return acceptLicenseDate;
    }

    public void setAcceptLicenseDate(Date acceptLicenseDate) {
        this.acceptLicenseDate = acceptLicenseDate;
    }

    public String getDrivingCarType() {
        return drivingCarType;
    }

    public void setDrivingCarType(String drivingCarType) {
        this.drivingCarType = drivingCarType;
    }
}
