package com.edu.ckl.springframeworkckl.resolver;

import com.edu.ckl.springframeworkckl.factory.BeanFactory;
import com.edu.ckl.springframeworkckl.ioc.RuntimeBeanReference;
import com.edu.ckl.springframeworkckl.ioc.TypeStringValue;

/**
 * @author chenkanglin
 * @desc
 *      处理 BeanDefinition 的Value
 *      判断是基础类型还是ref，如果是ref就要getBean然后set进属性
 * @Date 2020-09-18 22:05
 */
public class BeanDefinitionValueResolver {

    // 因为使用到 getBean() 所以需要注入
    private BeanFactory beanFactory;


    public BeanDefinitionValueResolver(BeanFactory beanFactory) {
        this.beanFactory = beanFactory;
    }

    /***
     * description:
     *      处理bean -》 property -》value、ref
     *      处理value，看一下是 简单类型 还是 ref
     * @param value
     * @return java.lang.Object
     */
    public Object resolveValue(Object value) {
        Object valueToUse = null;
        if(value instanceof TypeStringValue){
            TypeStringValue typeStringValue = (TypeStringValue) value;
            String stringValue = typeStringValue.getValue();
            Class<?> targetType = typeStringValue.getTargetType();
            //TODO
            valueToUse = handleType(stringValue, targetType);
        }else if(value instanceof RuntimeBeanReference){
            RuntimeBeanReference beanReference = (RuntimeBeanReference) value;
            String ref = beanReference.getRef();

            // 此处可能会发生循环依赖
            valueToUse = beanFactory.getBean(ref);
        }
        return valueToUse;
    }

    /***
     * description:
     *      类型转换
     * @param stringValue
     * @param targetType
     * @return java.lang.Object
     */
    public Object handleType(String stringValue, Class<?> targetType) {
        if(targetType == Integer.class){
            return Integer.parseInt(stringValue);
        }else if(targetType == String.class){
            return stringValue;
        }
        // TODO
        return null;
    }


}
