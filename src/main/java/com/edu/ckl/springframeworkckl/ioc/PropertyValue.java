package com.edu.ckl.springframeworkckl.ioc;

/**
 * @author chenkanglin
 * @desc
 *      PropertyValue 就封装着一个 property 标签的信息
 * @Date 2020-09-16 22:09
 */
public class PropertyValue {

    private String name;

    private Object value;

    public PropertyValue(String name, Object value) {
        this.name = name;
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Object getValue() {
        return value;
    }

    public void setValue(Object value) {
        this.value = value;
    }
}
