package com.edu.ckl.springframeworkckl.resource;

import java.io.InputStream;

/**
 * @author chenkanglin
 * @desc
 *      classpath 下的资源获取
 * @Date 2020-09-18 23:28
 */
public class ClasspathResource implements Resource {

    private String location;

    public ClasspathResource(String location) {
        this.location = location;
    }

    @Override
    public InputStream getResource() {
        if("classpath:".equals(location)){
            // 去除 classpath:
            location = location.substring(10);
        }
        return this.getClass().getClassLoader().getResourceAsStream(location);
    }
}
