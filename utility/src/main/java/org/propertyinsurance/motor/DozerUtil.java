package org.propertyinsurance.motor;

import com.google.common.collect.Lists;
import org.dozer.DozerBeanMapper;
import org.dozer.Mapper;

/**
 * User: zhenguang.zhu
 * Date: 12-8-21
 * Time: 下午4:19
 */
public final class DozerUtil {
    private static Mapper mapper = new DozerBeanMapper(Lists.newArrayList(
            "dozer.xml"));

    public static Mapper getMapper() {
        return mapper;
    }

    public static void map(Object source, Object destination) {
        getMapper().map(source, destination);
    }

    public static <T> T map(Object source, Class<T> destination) {
        return getMapper().map(source, destination);
    }
}
