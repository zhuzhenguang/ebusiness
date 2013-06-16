package org.propertyinsurance.motor;

import com.google.common.collect.Lists;

import java.util.List;

/**
 * 业务异常类.
 * <p/>
 * User: zhenguang.zhu
 * Date: 12-12-12
 * Time: 上午8:26
 */
public class BusinessException extends RuntimeException {
    private String code;
    private List<Object> exceptionList = Lists.newArrayList();

    public BusinessException() {
        super();
    }

    public BusinessException(String message) {
        super(message);
    }

    public BusinessException(String message, Throwable cause) {
        super(message, cause);
    }

    public BusinessException(Throwable cause) {
        super(cause);
    }

    public BusinessException(String code, String message) {
        this(message);
        setCode(code);
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public List<Object> getExceptionObject() {
        return exceptionList;
    }

    public void setExceptionObject(Object exceptionObject) {
        addExceptionObject(exceptionObject);
    }

    public void addExceptionObject(Object exceptionObject) {
        this.exceptionList.add(exceptionObject);
    }

    public static BusinessException create(Errors errors) {
        return new BusinessException(errors.getCode(), errors.getMessage());
    }

    public static BusinessException create(Errors errors, Exception e) {
        return new BusinessException(errors.getCode(), e.getMessage());
    }
}
