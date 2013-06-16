package org.propertyinsurance.motor.domain;

import org.propertyinsurance.motor.Assert;
import org.propertyinsurance.motor.BusinessException;
import org.propertyinsurance.motor.DozerUtil;
import org.propertyinsurance.motor.domain.area.Area;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.PrpCcarDriverDto;
import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.PrpCinsuredDto;

import java.util.HashSet;
import java.util.Set;

/**
 * 一般保险信息
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:19
 */
public class InsuranceInfo {
    private double loyaltyCoefficent;   // 忠诚度系数
    private String policyNo;              // 保单号
    private String lastYearPolicyNo;     // 上年度保单号
    private String demandNo;              // 投保查询码
    private boolean renewal;             // 是否续保

    private ViolationInfo violationInfo = new ViolationInfo();            // 违章信息
    private ClaimInfo claimInfo = new ClaimInfo();                        // 理赔信息
    private InsurancePeriod insurancePeriod = new InsurancePeriod();     // 保险期间对象
    private InsuredRelationPerson assured = new InsuredRelationPerson();  // 被保险人对象
    private InsuredRelationPerson proposal = new InsuredRelationPerson(); // 投保人对象
    private Set<Driver> driverSet = new HashSet<Driver>();                    // 驾驶员集合

    private Area area; // 地区

    public InsuranceInfo() {
    }

    public InsuranceInfo(Area area) {
        this.area = area;
    }

    /**
     * 是否忠诚
     *
     * @return
     */
    public boolean isLoyalty() {
        return getLoyaltyCoefficent() < 1;
    }

    /**
     * 委托函数，是否安全
     *
     * @return
     */
    public boolean isSafe() {
        return getViolationInfo().isSafe();
    }

    /**
     * 委托函数，是否无理赔
     *
     * @return
     */
    public boolean isNoClaim() {
        return getClaimInfo().isNoClaim();
    }

    /**
     * 增加一个理赔明细
     *
     * @param claimInfoDetail
     */
    public void addClaimDetail(ClaimInfoDetail claimInfoDetail) {
        getClaimInfo().getClaimInfoDetailSet().add(claimInfoDetail);
    }

    /**
     * 增加一个违章明细
     *
     * @param violationInfoDetail
     */
    public void addViolationDetail(ViolationInfoDetail violationInfoDetail) {
        getViolationInfo().getViolationInfoDetailSet().add(violationInfoDetail);
    }

    /**
     * 增加一个驾驶员
     *
     * @param driver
     */
    public void addDriver(Driver driver) {
        getDriverSet().add(driver);
    }

    /**
     * 设置投保关系人
     *
     * @param prpCinsuredDtos
     */
    public void setInsuranceRelatePersons(PrpCinsuredDto[] prpCinsuredDtos) {
        Assert.notEmpty(prpCinsuredDtos);

        for (PrpCinsuredDto prpCinsuredDto : prpCinsuredDtos) {
            InsuredRelationPerson person = DozerUtil.map(prpCinsuredDto, InsuredRelationPerson.class);
            person.setInsuredType(prpCinsuredDto.getInsuredFlag());
            if (person.getInsuredType() == InsuredType.Assured) {
                this.setAssured(person);
            } else if (person.getInsuredType() == InsuredType.Proposal) {
                this.setProposal(person);
            }
        }
    }

    /**
     * 设置驾驶员
     *
     * @param prpCcarDriverDtos
     */
    public void setDriverSet(PrpCcarDriverDto[] prpCcarDriverDtos) {
        Assert.notEmpty(prpCcarDriverDtos);

        for (PrpCcarDriverDto prpCcarDriverDto : prpCcarDriverDtos) {
            Driver driver = DozerUtil.map(prpCcarDriverDto, Driver.class);
            addDriver(driver);
        }
    }

    /**
     * 设置关系人
     *
     * @param person
     */
    public void setInsuredRelationPerson(InsuredRelationPerson person) {
        if (person.getInsuredType() == InsuredType.Proposal) {
            setProposal(person);
        } else if (person.getInsuredType() == InsuredType.Assured) {
            setAssured(person);
        }
    }

    /**
     * 检查保险期间合法性
     *
     * @return
     */
    public boolean checkLeadTime() throws BusinessException {
        return getInsurancePeriod().checkLeadTime(getArea());
    }

    /**
     * 检查是否脱保
     *
     * @return
     * @throws BusinessException
     */
    public boolean checkInsuranceExpire() throws BusinessException {
        return getInsurancePeriod().checkInsuranceExpire();
    }

    /**
     * 检查交强险是否重复投保
     *
     * @return
     */
    public boolean checkDoubleInsurance() {
        return getInsurancePeriod().checkDoubleInsurance(getArea());

    }



    public double getLoyaltyCoefficent() {
        return loyaltyCoefficent;
    }

    public void setLoyaltyCoefficent(double loyaltyCoefficent) {
        this.loyaltyCoefficent = loyaltyCoefficent;
    }

    public String getPolicyNo() {
        return policyNo;
    }

    public void setPolicyNo(String policyNo) {
        this.policyNo = policyNo;
    }

    public String getLastYearPolicyNo() {
        return lastYearPolicyNo;
    }

    public void setLastYearPolicyNo(String lastYearPolicyNo) {
        this.lastYearPolicyNo = lastYearPolicyNo;
    }

    public String getDemandNo() {
        return demandNo;
    }

    public void setDemandNo(String demandNo) {
        this.demandNo = demandNo;
    }

    public ViolationInfo getViolationInfo() {
        return violationInfo;
    }

    public void setViolationInfo(ViolationInfo violationInfo) {
        this.violationInfo = violationInfo;
    }

    public ClaimInfo getClaimInfo() {
        return claimInfo;
    }

    public void setClaimInfo(ClaimInfo claimInfo) {
        this.claimInfo = claimInfo;
    }

    public InsuredRelationPerson getAssured() {
        return assured;
    }

    public void setAssured(InsuredRelationPerson assured) {
        this.assured = assured;
    }

    public InsuredRelationPerson getProposal() {
        return proposal;
    }

    public void setProposal(InsuredRelationPerson proposal) {
        this.proposal = proposal;
    }

    public Set<Driver> getDriverSet() {
        return driverSet;
    }

    public InsurancePeriod getInsurancePeriod() {
        return insurancePeriod;
    }

    public void setInsurancePeriod(InsurancePeriod insurancePeriod) {
        this.insurancePeriod = insurancePeriod;
    }

    public Area getArea() {
        return area;
    }

    public boolean isRenewal() {
        return renewal;
    }

    public void setRenewal(boolean renewal) {
        this.renewal = renewal;
    }
}
