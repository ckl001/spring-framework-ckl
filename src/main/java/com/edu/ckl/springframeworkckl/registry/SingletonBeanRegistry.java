package com.edu.ckl.springframeworkckl.registry;

/**
 * @author chenkanglin
 * @desc
 *      提供对实现类中管理的单例Bean集合进行操作功能
 * @Date 2020-09-18 21:21
 */
public interface SingletonBeanRegistry {

    /***
     * description:
     *      获取单例对象
     * @param beanName
     * @return java.lang.Object
     */
    Object getSingleton(String beanName);


    /***
     * description:
     *      添加单例对象
     * @param beanName
     * @param bean
     * @return void
     */
    void addSingleton(String beanName, Object bean);
}
