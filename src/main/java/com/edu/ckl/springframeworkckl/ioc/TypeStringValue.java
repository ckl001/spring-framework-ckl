package com.edu.ckl.springframeworkckl.ioc;

/**
 * @author chenkanglin
 * @desc
 *      封装 <bean> 标签中子标签 <property> 的 value 属性值
 * @Date 2020-09-16 22:18
 */
public class TypeStringValue {

    // value属性值
    private String value;

    // value属性值对应的真正类型（bean中属性的值）
    private Class<?> targetType;

    public TypeStringValue(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Class<?> getTargetType() {
        return targetType;
    }

    public void setTargetType(Class<?> targetType) {
        this.targetType = targetType;
    }
}
