package com.edu.ckl.springframeworkckl.aware;

import com.edu.ckl.springframeworkckl.factory.BeanFactory;

/**
 * @author chenkanglin
 * @desc
 * @Date 2020-10-23 10:39
 */
public interface BeanFactoryAware extends Aware {

    void setBeanFactory(BeanFactory beanFactory);

}
