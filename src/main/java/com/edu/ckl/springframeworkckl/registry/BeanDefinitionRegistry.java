package com.edu.ckl.springframeworkckl.registry;

import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      BeanDefinition 管理
 * @Date 2020-09-18 21:33
 */
public interface BeanDefinitionRegistry {

    /***
     * description:
     *      获取 BeanDefinition
     * @param beanName
     * @return com.edu.ckl.springframeworkckl.ioc.BeanDefinition
     */
    BeanDefinition getBeanDefinition(String beanName);


    /***
     * description:
     *      获取所有 BeanDefinition
     * @param
     * @return java.util.List<com.edu.ckl.springframeworkckl.ioc.BeanDefinition>
     */
    List<BeanDefinition> getBeanDefinition();

    /***
     * description:
     *      注册 BeanDefinition
     * @param beanName
     * @param beanDefinition
     * @return void
     */
    void registerBeanBeanDefinition(String beanName, BeanDefinition beanDefinition);
}
