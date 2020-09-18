package com.edu.ckl.springframeworkckl.resource;


import java.io.InputStream;

/**
 * @author chenkanglin
 * @Desc
 *      提供对资源的操作功能
 *      （资源可能存在磁盘、网络、classpath下）
 */
public interface Resource {

    /***
     * description:
     *      获取资源的流
     * @param
     * @return java.io.InputStream
     */
    InputStream getResource();

}
