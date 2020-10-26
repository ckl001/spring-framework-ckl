package com.edu.ckl.springframeworkckl.factory.support;

import com.edu.ckl.springframeworkckl.factory.ListableBeanFactory;
import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;
import com.edu.ckl.springframeworkckl.registry.BeanDefinitionRegistry;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author chenkanglin
 * @desc
 *      通过BeanDefinitionRegistry 接口暴露 DefaultListableBeanFactory，
 *      符合最少认知原则 迪米特法则
 * @Date 2020-09-18 21:39
 */
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry, ListableBeanFactory {

    private Map<String,BeanDefinition> beanDefinitions = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitions.get(beanName);
    }

    @Override
    public List<BeanDefinition> getBeanDefinitions() {
        List<BeanDefinition> beanDefinitionList = new ArrayList<>();
        for (BeanDefinition bd : beanDefinitions.values()){
            beanDefinitionList.add(bd);
        }
        return beanDefinitionList;    }

    @Override
    public void registerBeanBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName, beanDefinition);
    }


    /***
     * description:
     *
     * @param type 父类型
     * @return java.util.List<T>
     */
    @Override
    public <T> List<T> getBeansByType(Class type) {
        List<T> results = new ArrayList<>();
        List<BeanDefinition> beanDefinitions = getBeanDefinitions();
        for (BeanDefinition beanDefinition : beanDefinitions) {
            // 容器类当前类的类型
            Class<?> clazzType = beanDefinition.getClazzType();
            if(type.isAssignableFrom(clazzType)){
                results.add((T) getBean(beanDefinition.getBeanName()));
            }
        }
        return results;
    }

    @Override
    public List<String> getBeanNamesByType(Class type) {
        return null;
    }
}
