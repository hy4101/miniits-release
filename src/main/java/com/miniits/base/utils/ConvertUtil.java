package com.miniits.base.utils;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.BeanUtils;
import sun.misc.BASE64Encoder;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Method;
import java.util.*;

/**
 * @author: WWW.MINIITS.COM
 * @Date: 2018/1/7
 * @Time: 11:56
 * <p>
 * Description:
 * ***
 */
public class ConvertUtil {

    /**
     * 单个对象拷贝
     * 例子：convertToModel(order,OrderVO.class,null)
     *
     * @param source    数据源
     * @param targetCls 目标对象Class
     * @param <T>
     * @return
     */
    public static <T> T toVO(Object source, Class<T> targetCls) {
        if (source == null) {
            return null;
        }
        T target = BeanUtils.instantiate(targetCls);
        BeanUtils.copyProperties(source, target);
        return target;
    }

    /**
     * List对象拷贝
     * 例子：(List<OrderVO>) convertToModels(orders, OrderVO.class, null);
     *
     * @param sources
     * @param targetCls
     * @param <T>
     * @return
     */
    public static <T> Collection<T> toVOS(Collection sources, Class<T> targetCls) {
        if (sources == null) {
            return null;
        }
        List<T> targets = new ArrayList<T>();
        Iterator iterator = sources.iterator();
        while (iterator.hasNext()) {
            Object source = iterator.next();

            T target = BeanUtils.instantiate(targetCls);
            BeanUtils.copyProperties(source, target);
            targets.add(target);
        }

        return targets;
    }

    protected static String[] propertyDiffer(String properties, Class targetCls) {
        if (StringUtils.isBlank(properties)) {
            return null;
        }

        PropertyDescriptor[] targetPds = BeanUtils.getPropertyDescriptors(targetCls);
        List<String> propertiesList = Arrays.asList(properties.split(","));
        List<String> arrayList = new ArrayList<>();

        for (PropertyDescriptor targetPd : targetPds) {
            Method writeMethod = targetPd.getWriteMethod();
            if (writeMethod != null && !propertiesList.contains(targetPd.getName())) {
                arrayList.add(targetPd.getName());
            }
        }
        return arrayList.toArray(new String[arrayList.size()]);
    }

    public static String convertThunderUrl(String url) {
        url = "AA" + url + "ZZ";
        return "miniDownloadthunder://" + new BASE64Encoder().encode(url.getBytes()).replaceAll("\r\n", "");
    }
}