package com.edu.ckl.springframeworkckl.factory;

/**
 * @author chenkanglin
 * @desc
 *      spring 容器的定级接口
 * @Date 2020-09-18 20:56
 */
public interface BeanFactory {

    /***
     * description:
     *      根据bean的名称获取对应的实例
     * @param beanName
     * @return java.lang.Object
     */
    Object getBean(String beanName);
}
