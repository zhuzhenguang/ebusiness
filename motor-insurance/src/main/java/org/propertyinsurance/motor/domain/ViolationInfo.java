package org.propertyinsurance.motor.domain;

import com.google.common.collect.Sets;
import org.apache.commons.lang.ArrayUtils;

import java.util.Set;

/**
 * 违章信息
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-7
 * Time: 上午10:23
 */
public class ViolationInfo {
    private static final String VIOLATION_RULE_DESC = "机动车未按规定鸣喇叭示意的/驾驶人未按规定使用安全带的/" +
            "机动车违反规定停放、临时停车且驾驶人不在现场或驾驶人虽在现场拒绝立即驶离，妨碍其它车辆、行人通行的";
    private double violationCoefficent; // 违章系数
    private int violationCount = -1;     // 违章次数

    private Set<ViolationInfoDetail> violationInfoDetailSet = Sets.newHashSet(); // 违章详细信息

    /**
     * 是否安全
     *
     * @return
     */
    public boolean isSafe() {
        return getViolationCoefficent() < 1;
    }

    /**
     * 获得违章次数，需要过滤
     *
     * @return
     */
    public int getViolationCount() {
        if (this.violationCount < 0) {
            int violationCount = 0;
            String[] violationDescs = VIOLATION_RULE_DESC.split("/");
            for (ViolationInfoDetail detail : violationInfoDetailSet) {
                if (ArrayUtils.contains(violationDescs, detail.getViolationActionDesc()))
                    violationCount++;
            }
            setViolationCount(violationCount);
        }
        return this.violationCount;
    }

    public double getViolationCoefficent() {
        return violationCoefficent;
    }

    public void setViolationCoefficent(double violationCoefficent) {
        this.violationCoefficent = violationCoefficent;
    }

    public void setViolationCount(int violationCount) {
        this.violationCount = violationCount;
    }

    public Set<ViolationInfoDetail> getViolationInfoDetailSet() {
        return violationInfoDetailSet;
    }

    public void setViolationInfoDetailSet(Set<ViolationInfoDetail> violationInfoDetailSet) {
        this.violationInfoDetailSet = violationInfoDetailSet;
    }
}
