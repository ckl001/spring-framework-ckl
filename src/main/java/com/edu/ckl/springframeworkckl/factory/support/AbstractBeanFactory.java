package com.edu.ckl.springframeworkckl.factory.support;

import com.edu.ckl.springframeworkckl.factory.BeanFactory;
import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;
import com.edu.ckl.springframeworkckl.registry.DefaultSingletonBeanRegistry;

/**
 * @author chenkanglin
 * @desc
 *      BeanFactory 的抽象实现
 * @Date 2020-09-18 21:16
 */
public abstract class AbstractBeanFactory extends DefaultSingletonBeanRegistry implements BeanFactory {


    @Override
    public Object getBean(String beanName) {

        // 1、先从 DefaultSingletonBeanRegistry 单例Bean集合中找
        Object singletonObject = getSingleton(beanName);

        if(null != singletonObject){
            return singletonObject;
        }

        // 2、如果不存在，则从BeanDefinition集合中找
        BeanDefinition beanDefinition = getBeanDefinition(beanName);
        if(null == beanDefinition){
            return null;
        }

        // 3、判断该Bean是单例还是多例
//        String scope = bd.getScope();
//        if ("singleton".equals(scope)){
//
//        }else if("prototype".equals(scope)){
//
//        }

        // 4、通过BeanDefinition创建 bean
        if(beanDefinition.isSingleton()){
            singletonObject = createBean(beanDefinition);
            // 5、创建完bean之后，放入到单例Bean集合中
            addSingleton(beanName,singletonObject);
        }else if(beanDefinition.isPrototype()){
            //多例,每次都创建一个
            singletonObject = createBean(beanDefinition);

        }

        return singletonObject;
    }

    /***
     * description:
     *      使用抽象模板方法，将真正的实现逻辑延迟到子类（DefaultListableBeanFactory中实现）
     * @param beanName
     * @return com.edu.ckl.springframeworkckl.ioc.BeanDefinition
     */
    protected abstract BeanDefinition getBeanDefinition(String beanName);


    /***
     * description:
     *      使用抽象模板方法，将真正的实现逻辑延迟到子类（AbstractAutowireCapableBeanFactory中实现）
     * @param beanDefinition
     * @return java.lang.Object
     */
    protected abstract Object createBean(BeanDefinition beanDefinition);

}
