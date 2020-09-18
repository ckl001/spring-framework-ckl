package com.edu.ckl.springframeworkckl.factory;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      可列表化操作的Bean工厂
 * @Date 2020-09-18 21:01
 */
public interface ListableBeanFactory extends BeanFactory {

    /***
     * description:
     *      根据给定的类型，获取它以及子类型的所有的实例
     * @param type
     * @return java.util.List<T>
     */
    <T> List<T> getBeansByType(Class type);

    /***
     * description:
     *
     * @param type
     * @return java.util.List<java.lang.String>
     */
    List<String> getBeanNamesByType(Class type);



}
