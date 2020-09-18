package com.edu.ckl.springframeworkckl.ioc;

import java.util.ArrayList;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      <bean></bean> 的标签系信息
 * @Date 2020-09-16 11:42
 */
public class BeanDefinition {

    // bean 标签的 class 属性
    private String clazzName;

    // bean 标签的 class 属性对应的 Class 对象
    private Class<?> clazzType;

    // bean 标签的id属性和name属性都会存储到该属性值，id和name属性是二选一使用的
    private String beanName;

    // 初始化方法名称
    private String initMethod;

    // 该信息的默认的配置，如果不配置就默认是 singleton
    private String scope;

    // bean 中的属性信息
    private List<PropertyValue> propertyValues = new ArrayList<>();




    private static final String SCOPE_SINGLETON = "singleton";
    private static final String SCOPE_PROTOTYPE = "prototype";

    public boolean isSingleton() {
        return SCOPE_SINGLETON.equals(this.scope);
    }

    public boolean isPrototype() {
        return SCOPE_PROTOTYPE.equals(this.scope);
    }



    public BeanDefinition(String clazzName, String beanName) {
        this.beanName = beanName;
        this.clazzName = clazzName;
        this.clazzType = resolveClassName(clazzName);
    }

    private Class<?> resolveClassName(String clazzName) {
        try {
            return Class.forName(clazzName);
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public String getClazzName() {
        return clazzName;
    }

    public void setClazzName(String clazzName) {
        this.clazzName = clazzName;
    }

    public Class<?> getClazzType() {
        return clazzType;
    }

    public void setClazzType(Class<?> clazzType) {
        this.clazzType = clazzType;
    }

    public String getBeanName() {
        return beanName;
    }

    public void setBeanName(String beanName) {
        this.beanName = beanName;
    }

    public String getInitMethod() {
        return initMethod;
    }

    public void setInitMethod(String initMethod) {
        this.initMethod = initMethod;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public List<PropertyValue> getPropertyValues() {
        return propertyValues;
    }

    public void setPropertyValues(List<PropertyValue> propertyValues) {
        this.propertyValues = propertyValues;
    }

    public void addPropertyValue(PropertyValue propertyValue) {
        this.propertyValues.add(propertyValue);
    }
}
