package org.propertyinsurance.motor;

import org.apache.commons.lang.ArrayUtils;

/**
 * 断言类
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-8-17
 * Time: 上午11:15
 */
public final class Assert {
    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new BusinessException(message);
        }
    }

    public static void notNull(Object o) {
        notNull(o, "The object could not be null");
    }

    public static void notEmpty(Object[] o) {
        if (ArrayUtils.isEmpty(o)) {
            throw new BusinessException("Array could not be null");
        }
    }

}
