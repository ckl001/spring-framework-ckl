package com.edu.ckl.springframeworkckl.factory.support;

import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;
import com.edu.ckl.springframeworkckl.registry.BeanDefinitionRegistry;

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
public class DefaultListableBeanFactory extends AbstractAutowireCapableBeanFactory implements BeanDefinitionRegistry {

    private Map<String,BeanDefinition> beanDefinitions = new HashMap<>();

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return this.beanDefinitions.get(beanName);
    }

    @Override
    public List<BeanDefinition> getBeanDefinition() {
        return (List<BeanDefinition>) this.beanDefinitions.values();
    }

    @Override
    public void registerBeanBeanDefinition(String beanName, BeanDefinition beanDefinition) {
        this.beanDefinitions.put(beanName, beanDefinition);
    }
}
