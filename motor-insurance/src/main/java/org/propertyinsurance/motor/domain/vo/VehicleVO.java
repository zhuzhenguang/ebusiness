package org.propertyinsurance.motor.domain.vo;

import com.sinosoft.ebusiness.thirdparty.prpall.client.model.carquery.VehiclePriceOutputDto;

/**
 * 车辆信息VO
 *
 * User: zhenguang.zhu
 * Date: 13-1-21
 * Time: 下午11:18
 */
public class VehicleVO {
    private VehiclePriceOutputDto[] vehiclePrices;
    private int selectedNo = -1;       // 选择第几个车辆信息
    private String jingyouCode;        // 精友数据代码

    public VehicleVO(VehiclePriceOutputDto[] vehiclePrices) {
        this.vehiclePrices = vehiclePrices;
    }

    public VehicleVO(int selectedNo, String jingyouCode) {
        this.selectedNo = selectedNo;
        this.jingyouCode = jingyouCode;
        if (!isNeedSelect()) {
            this.vehiclePrices = null;
        }
    }

    /**
     * 是否需要用户选择
     */
    public boolean isNeedSelect() {
        return getSelectedNo() < 0;
    }

    public VehiclePriceOutputDto[] getVehiclePrices() {
        return vehiclePrices;
    }

    public int getSelectedNo() {
        return selectedNo;
    }

    public String getJingyouCode() {
        return jingyouCode;
    }
}
