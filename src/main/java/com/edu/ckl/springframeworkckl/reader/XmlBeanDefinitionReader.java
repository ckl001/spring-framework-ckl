package com.edu.ckl.springframeworkckl.reader;

import com.edu.ckl.springframeworkckl.registry.BeanDefinitionRegistry;
import com.edu.ckl.springframeworkckl.resource.Resource;
import com.edu.ckl.springframeworkckl.utils.DocumentUtils;
import org.dom4j.Document;

import java.io.InputStream;

/**
 * @author chenkanglin
 * @desc
 *      功能跟 XmlBeanDefinitionDocumentReader一样
 *      XmlBeanDefinitionReader --> XmlBeanDefinitionDocumentReader
 * @Date 2020-09-18 23:13
 */
public class XmlBeanDefinitionReader {

    private BeanDefinitionRegistry beanDefinitionRegistry;

    public XmlBeanDefinitionReader(BeanDefinitionRegistry beanDefinitionRegistry) {
        this.beanDefinitionRegistry = beanDefinitionRegistry;
    }

    public void loadBeanDefinitions(InputStream in){
        // 创建文档Document对象
        Document document = DocumentUtils.getDocument(in);
        XmlBeanDefinitionDocumentReader xmlBeanDefinitionDocumentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        xmlBeanDefinitionDocumentReader.registerBeanDefinitions(document.getRootElement());

    }

    public void loadBeanDefinitions(Resource resource) {
        InputStream inputStream = resource.getResource();

        doLoadBeanDefinitions(inputStream);
    }

    private void doLoadBeanDefinitions(InputStream inputStream) {
        // 创建文档对象
        Document document = DocumentUtils.getDocument(inputStream);
        XmlBeanDefinitionDocumentReader documentReader = new XmlBeanDefinitionDocumentReader(beanDefinitionRegistry);
        documentReader.registerBeanDefinitions(document.getRootElement());
    }

}
