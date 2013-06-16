package org.propertyinsurance.motor.domain;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.poliquery.PrpCitemKindDto;
import org.apache.commons.lang.ArrayUtils;
import org.propertyinsurance.motor.DozerUtil;

import java.util.HashSet;
import java.util.Set;

/**
 * 投保险别保费信息
 * <p/>
 * User: zhenguang.zhu
 * Date: 13-1-11
 * Time: 下午12:15
 */
public class PremiumInfo {
    private Set<InsuranceKind> insuranceKindSet = new HashSet<InsuranceKind>();

    /**
     * 通过险别数组来创建保费信息
     *
     * @param prpCitemKindDtos
     * @return
     */
    public static PremiumInfo createWithKinds(PrpCitemKindDto[] prpCitemKindDtos) {
        PremiumInfo premiumInfo = new PremiumInfo();
        if (ArrayUtils.isNotEmpty(prpCitemKindDtos)) {
            for (PrpCitemKindDto prpCitemKindDto : prpCitemKindDtos) {
                InsuranceKind insuranceKind = DozerUtil.map(prpCitemKindDto, InsuranceKind.class);
                premiumInfo.addInsuranceKind(insuranceKind);
            }
        }
        return premiumInfo;
    }

    /*
     * 增加险别
     */
    public void addInsuranceKind(InsuranceKind insuranceKind) {
        insuranceKindSet.add(insuranceKind);
    }

    public Set<InsuranceKind> getInsuranceKindSet() {
        return insuranceKindSet;
    }
}
