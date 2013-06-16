package org.propertyinsurance.motor;

import com.samsungproperty.motor.DozerUtil;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * dozer工具的测试
 *
 * User: zhenguang.zhu
 * Date: 13-1-21
 * Time: 下午11:23
 */
public class DozerTest {
    @Test
    public void testDozer() {
        Person person = new Person();
        person.setName("zhuzhu");

        Author author = DozerUtil.map(person, Author.class);
        assertEquals("zhuzhu", author.getName());
    }
}
