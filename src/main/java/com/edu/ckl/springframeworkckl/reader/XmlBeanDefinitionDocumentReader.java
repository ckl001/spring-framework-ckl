package com.edu.ckl.springframeworkckl.reader;

import com.edu.ckl.springframeworkckl.ioc.BeanDefinition;
import com.edu.ckl.springframeworkckl.ioc.PropertyValue;
import com.edu.ckl.springframeworkckl.ioc.RuntimeBeanReference;
import com.edu.ckl.springframeworkckl.ioc.TypeStringValue;
import com.edu.ckl.springframeworkckl.registry.BeanDefinitionRegistry;
import com.edu.ckl.springframeworkckl.utils.ReflectUtils;
import org.dom4j.Element;

import java.util.List;

/**
 * @author chenkanglin
 * @desc
 *      按照spring中xml的配置文件（bean.xml文件）的语义解析，
 *      将解析之后的记过存储到 BeanDefinitionRegistry
 * @Date 2020-09-18 23:05
 */
public class XmlBeanDefinitionDocumentReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionDocumentReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    /***
     * description:
     *          解析 <beans>
     * @param rootElement
     * @return void
     */
    public void registerBeanDefinitions(Element rootElement) {
        // 获取<bean>和自定义标签（mvc:interceptors）
        List<Element> elements = rootElement.elements();
        for (Element element : elements) {
            // 获取标签名称
            String name = element.getName();
            if("bean".equals(name)){
                //解析默认标签 ： bean 标签
                parseDefaultElement(element);
            }else{
                // 解析自定义标签：mvc:interceptors
                parseCustomElement(element);
            }
        }
    }

    /***
     * description:
     *     解析自定义标签：mvc:interceptors
     * @param element
     * @return void
     */
    public void parseCustomElement(Element element) {

    }

    /***
     * description:
     *     解析<bean></bean> 标签
     * @param beanElement
     * @return void
     */
    @SuppressWarnings("unchecked")
    public void parseDefaultElement(Element beanElement) {

        try {
            if(null == beanElement){
                return;
            }
            // 获取相关属性
            // 获取id属性
            String id = beanElement.attributeValue("id");

            // 获取name属性
            String name = beanElement.attributeValue("name");

            // 获取class属性
            String clazzName = beanElement.attributeValue("class");
            if(null == clazzName || "".equals(clazzName)){
                return;
            }

            // 获取init-method属性
            String initMethod = beanElement.attributeValue("init-method");
            // 获取scope属性
            String scope = beanElement.attributeValue("scope");
            scope = scope != null && !scope.equals("") ? scope : "singleton";

            // 获取beanName beanName和id二选一
            String beanName = null == id ? name : id;
            Class<?> classType = Class.forName(clazzName);

            beanName = null == beanName ? classType.getSimpleName() : beanName;

            // 创建BeanDefinition对象
            BeanDefinition beanDefinition = new BeanDefinition(clazzName, beanName);
            beanDefinition.setInitMethod(initMethod);
            beanDefinition.setScope(scope);

            // 获取propertyValue的信息
            List<Element> propertyElements = beanElement.elements();
            for (Element propertyElement : propertyElements) {
                parsePropertyElement(beanDefinition, propertyElement);
            }

            // 注册BeanDefinition 的信息
            beanDefinitionRegistry.registerBeanBeanDefinition(beanName, beanDefinition);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /***
     * description:
     *      解析 bean 中的property属性 并 set 到 BeanDefiniton
     * @param beanDefinition
     * @param propertyElement
     * @return void
     */
    public void parsePropertyElement(BeanDefinition beanDefinition, Element propertyElement) {
        if(null == propertyElement){
            return;
        }
        // 获取name 属性
        String name = propertyElement.attributeValue("name");
        //value
        String value = propertyElement.attributeValue("value");
        //ref
        String ref = propertyElement.attributeValue("ref");

        // 如果value和ref都有值，则返回； 不能同时存在
        if (value != null && !value.equals("") && ref != null && !ref.equals("")) {
            return;
        }

        // propertyValue 封装property的标签信息
        PropertyValue propertyValue = null;
        if(value != null && !value.equals("")){
            // 因为spring配置文件中的value是String类型，而对象中的属性值是各种各样的，所以需要存储类型
            TypeStringValue typeStringValue = new TypeStringValue(value);

            Class<?> targetType = ReflectUtils.getTypeByFieldName(beanDefinition.getClazzName(), name);
            typeStringValue.setTargetType(targetType);

            propertyValue = new PropertyValue(name, typeStringValue);
            beanDefinition.addPropertyValue(propertyValue);
        }else if(ref != null && !ref.equals("")){
            RuntimeBeanReference reference = new RuntimeBeanReference(ref);
            propertyValue = new PropertyValue(name, reference);
            beanDefinition.addPropertyValue(propertyValue);
        }else {
            return;
        }

    }
}

