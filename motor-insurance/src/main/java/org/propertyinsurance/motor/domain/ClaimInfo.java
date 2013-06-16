package org.propertyinsurance.motor.domain;

import com.google.common.collect.Sets;

import java.util.Set;

/**
 * 理赔信息
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:22
 */
public class ClaimInfo {
    private double claimCoefficent;     // 理赔系数
    private Set<ClaimInfoDetail> claimInfoDetailSet = Sets.newHashSet(); // 理赔详细信息

    /**
     * 是否无理赔
     *
     * @return
     */
    public boolean isNoClaim() {
        return getClaimCoefficent() < 1;
    }

    /**
     * 获得理赔次数
     *
     * @return
     */
    public int getClaimCount() {
        return getClaimInfoDetailSet().size();
    }

    public double getClaimCoefficent() {
        return claimCoefficent;
    }

    public void setClaimCoefficent(double claimCoefficent) {
        this.claimCoefficent = claimCoefficent;
    }

    public Set<ClaimInfoDetail> getClaimInfoDetailSet() {
        return claimInfoDetailSet;
    }

    public void setClaimInfoDetailSet(Set<ClaimInfoDetail> claimInfoDetailSet) {
        this.claimInfoDetailSet = claimInfoDetailSet;
    }

}
