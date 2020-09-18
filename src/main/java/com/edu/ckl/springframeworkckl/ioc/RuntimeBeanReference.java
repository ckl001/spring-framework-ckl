package com.edu.ckl.springframeworkckl.ioc;

/**
 * @author chenkanglin
 * @desc
 *      封装 <bean> 标签中子标签 <property> 的 ref 属性值
 * @Date 2020-09-16 22:22
 */
public class RuntimeBeanReference {

    // ref的属性值
    private String ref;

    public String getRef() {
        return ref;
    }

    public void setRef(String ref) {
        this.ref = ref;
    }

    public RuntimeBeanReference(String ref) {
        super();
        this.ref = ref;
    }

}
