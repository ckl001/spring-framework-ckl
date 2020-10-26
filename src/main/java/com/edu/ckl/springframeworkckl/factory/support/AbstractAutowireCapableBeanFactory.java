package com.edu.ckl.springframeworkckl.factory.support;


import com.edu.ckl.springframeworkckl.aware.Aware;
import com.edu.ckl.springframeworkckl.aware.BeanFactoryAware;
import com.edu.ckl.springframeworkckl.factory.AutowireCapableBeanFactory;
import com.edu.ckl.springframeworkckl.factory.BeanFactory;
import com.edu.ckl.springframeworkckl.init.InitializingBean;
import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;
import com.edu.ckl.springframeworkckl.ioc.PropertyValue;
import com.edu.ckl.springframeworkckl.resolver.BeanDefinitionValueResolver;
import com.edu.ckl.springframeworkckl.utils.ReflectUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *     真正实现Bean的创建的类
 *     负责实现 BeanFactory 的 createBean();
 * @Date 2020-09-18 21:11
 */
public abstract class AbstractAutowireCapableBeanFactory extends AbstractBeanFactory implements AutowireCapableBeanFactory {

    /***
     * description:
     *          通过beanDefinition信息创建bean
     * @param beanDefinition
     * @return java.lang.Object
     */
    @Override
    protected Object createBean(BeanDefinition beanDefinition) {
        Object bean = null;
        try {
            // 4.1 Bean 的实例化 （new）
            bean = createBeanInstance(beanDefinition);

            // 4.2 Bean 的属性填充（依赖注入）
            populateBean(bean,beanDefinition);

            // 4.3 Bean 的初始化（调用初识方法，完成初始化 init-method）
            initializeBean(bean,beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }

    /***
     * description:
     *      4.3 Bean 的初始化（调用初识方法，完成初始化 init-method）
     * @param bean
     * @param beanDefinition
     * @return void
     */
    private void initializeBean(Object bean, BeanDefinition beanDefinition) throws Exception {

        // 处理Aware接口
        if(bean instanceof Aware){
            if(bean instanceof BeanFactoryAware){
                ((BeanFactoryAware) bean).setBeanFactory(this);
            }
        }

        // 处理InitializingBean接口的初始化操作
        if(bean instanceof InitializingBean){
            ((InitializingBean) bean).afterPropertiesSet();
        }

        // 处理init-method标签属性对应的初始化方法
        invokeInitMethod(bean, beanDefinition);

    }


    private void invokeInitMethod(Object bean, BeanDefinition bd) throws Exception{
        String initMethod = bd.getInitMethod();
        if (initMethod == null){
            return;
        }

        ReflectUtils.invokeMethod(bean,initMethod);

    }

    /***
     * description:
     *      4.2 Bean 的属性填充（依赖注入）
     * @param bean
     * @param beanDefinition
     * @return void
     */
    private void populateBean(Object bean, BeanDefinition beanDefinition)  {
        List<PropertyValue> propertyValues = beanDefinition.getPropertyValues();
        for (PropertyValue propertyValue : propertyValues) {
            String name = propertyValue.getName();
            Object value = propertyValue.getValue();

            //处理value，看一下是 简单类型 还是 ref
            BeanDefinitionValueResolver beanDefinitionValueResolver = new BeanDefinitionValueResolver(this);
            Object valueToUse = beanDefinitionValueResolver.resolveValue(value);

            ReflectUtils.setProperty(bean, name, valueToUse);
        }
    }

    /***
     * description:
     *         4.1 Bean 的实例化 （new）
     * @param beanDefinition
     * @return java.lang.Object
     */
    private Object createBeanInstance(BeanDefinition beanDefinition) {
//        Object bean = null;
//        try {
//            // TODO 1、通过静态工厂方法去创建Bean的实例
//            // TODO 2、通过对象工厂去创建Bean的实例
        Object bean = ReflectUtils.createBean(beanDefinition.getClazzType());

//            // 3、通过构造方法创建bean实例
//            Class<?> clazzType = beanDefinition.getClazzType();
//            Constructor constructor = clazzType.getDeclaredConstructor();
//            bean = constructor.newInstance();
//        } catch (NoSuchMethodException e) {
//            e.printStackTrace();
//        } catch (InstantiationException e) {
//            e.printStackTrace();
//        } catch (IllegalAccessException e) {
//            e.printStackTrace();
//        } catch (InvocationTargetException e) {
//            e.printStackTrace();
//        }
        return bean;
    }

}
