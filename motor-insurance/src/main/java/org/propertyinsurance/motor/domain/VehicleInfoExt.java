package org.propertyinsurance.motor.domain;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.VehiclePriceOutputDto;

/**
 * 车型信息.(非核心)
 *
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 上午12:12
 */
public class VehicleInfoExt {
    private int selectedNo = -1;       // 选择第几个车辆信息
    private String jingyouCode;        // 精友数据代码
    private VehiclePriceOutputDto[] vehiclePrices;

    /**
     * 是否需要用户选择
     */
    public boolean isNeedSelect() {
        return getSelectedNo() >= 0;
    }

    public int getSelectedNo() {
        return selectedNo;
    }

    public void setSelectedNo(int selectedNo) {
        this.selectedNo = selectedNo;
    }

    public String getJingyouCode() {
        return jingyouCode;
    }

    public void setJingyouCode(String jingyouCode) {
        this.jingyouCode = jingyouCode;
    }

    public VehiclePriceOutputDto[] getVehiclePrices() {
        return vehiclePrices;
    }

    public void setVehiclePrices(VehiclePriceOutputDto[] vehiclePrices) {
        this.vehiclePrices = vehiclePrices;
    }
}
