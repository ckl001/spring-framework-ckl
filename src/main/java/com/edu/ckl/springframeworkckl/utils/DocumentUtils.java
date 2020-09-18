package com.edu.ckl.springframeworkckl.utils;

import org.dom4j.Document;
import org.dom4j.io.SAXReader;

import java.io.InputStream;

/**
 * @author chenkanglin
 * @desc
 *      xml解析工具
 * @Date 2020-09-18 22:52
 */
public class DocumentUtils {

    public static Document getDocument(InputStream inputStream){
        try {
            SAXReader reader = new SAXReader();
            return reader.read(inputStream);
        }catch (Exception e){
            e.printStackTrace();
        }
        return null;
    }
}
