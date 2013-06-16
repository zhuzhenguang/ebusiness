package org.propertyinsurance.motor.domain.schema;

import org.propertyinsurance.motor.domain.state.State;

/**
 * 业务计划工厂
 *
 * User: zhenguang.zhu
 * Date: 12-12-9
 * Time: 下午6:15
 */
public class SchemaFactory {
    public static SchemaService getSchema(State state) {
        return new ShangHaiLicenseSchema();
    }

}
