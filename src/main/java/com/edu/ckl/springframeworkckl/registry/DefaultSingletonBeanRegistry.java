package com.edu.ckl.springframeworkckl.registry;

/**
 * @author chenkanglin
 * @desc
 *      为什么要单独封装这个类？
 *      因为该类中需要对单例bean集合进行很严格的管理（线程安全问题）
 * @Date 2020-09-18 21:24
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {


    @Override
    public Object getSingleton(String beanName) {
        return null;
    }


    @Override
    public void addSingleton(String beanName, Object bean) {

    }
}
