package com.edu.ckl.springframeworkckl.registry;

import java.util.HashMap;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 *      为什么要单独封装这个类？
 *      因为该类中需要对单例bean集合进行很严格的管理（线程安全问题）
 * @Date 2020-09-18 21:24
 */
public class DefaultSingletonBeanRegistry implements SingletonBeanRegistry {

    private Map<String,Object> singletonObjects = new HashMap<>();


    @Override
    public Object getSingleton(String beanName) {
        return this.singletonObjects.get(beanName);
    }


    @Override
    public void addSingleton(String beanName, Object bean) {
        //TODO 可以使用双重检查锁的方式进行单例的处理
        this.singletonObjects.put(beanName,bean);
    }
}
