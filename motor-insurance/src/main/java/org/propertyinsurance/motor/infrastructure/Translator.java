package org.propertyinsurance.motor.infrastructure;

import org.propertyinsurance.motor.BusinessException;

/**
 * 翻译器，用于核心系统和网销系统之间转换数据
 * <p/>
 * User: zhu
 * Date: 13-2-14
 * Time: 下午5:13
 */
public interface Translator {
    void translate() throws BusinessException;
}
