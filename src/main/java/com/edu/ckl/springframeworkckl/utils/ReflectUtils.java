package com.edu.ckl.springframeworkckl.utils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author chenkanglin
 * @desc
 *      反射工具类
 * @Date 2020-09-18 22:14
 */
public class ReflectUtils {


    /***
     * description:
     *      通过无参构造器创建Bean
     * @param clazz
     * @return java.lang.Object
     */
    public static Object createBean(Class clazz){
        try {
            Constructor<?> constructor = clazz.getDeclaredConstructor();
            return constructor.newInstance();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }

        return null;
    }



    public static void invokeMethod(Object bean, String initMethod){
        try {
            Class<?> clazz = bean.getClass();
            Method method = clazz.getDeclaredMethod(initMethod);
            method.setAccessible(true);
            method.invoke(bean);
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } finally {
        }
    }



    public static void setProperty(Object bean, String name, Object valueToUse){
        try {
            Class<?> aClass = bean.getClass();
            Field field = aClass.getDeclaredField(name);
            field.setAccessible(true);
            field.set(bean, valueToUse);
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } finally {
        }
    }


    /***
     * description:
     *      获取属性返回值类型
     * @param beanClassName
     * @param name
     * @return java.lang.Class<?>
     */
    public static Class<?> getTypeByFieldName(String beanClassName, String name){
        try {
            Class<?> clazz = Class.forName(beanClassName);
            Field field = clazz.getDeclaredField(name);
            return field.getType();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (NoSuchFieldException e) {
            e.printStackTrace();
        } finally {
        }
        return null;

    }

}
