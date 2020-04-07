package com.geekerstar.skills;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author geekerstar
 * @date 2020/4/7 10:48
 * @description
 */
public class ClassSnippets {

    /**
     * 此方法返回由给定类及其超类实现的所有接口。
     * 该方法通过连接两个Stream来工作。第一个Stream是通过创建带有接口的流和接口实现的所有接口来递归构建的。 第二个Stream对超类也是如此。其结果是删除重复项后将两个Stream连接起来。
     *
     * @param cls
     * @return
     */
    public static List<Class<?>> getAllInterfaces(Class<?> cls) {
        return Stream.concat(
                Arrays.stream(cls.getInterfaces()).flatMap(intf ->
                        Stream.concat(Stream.of(intf), getAllInterfaces(intf).stream())),
                cls.getSuperclass() == null ? Stream.empty() : getAllInterfaces(cls.getSuperclass()).stream()
        ).distinct().collect(Collectors.toList());
    }


    /**
     * 此方法检查指定的类是内部类还是静态嵌套类。
     *
     * @param cls
     * @return
     */
    public static boolean isInnerClass(final Class<?> cls) {
        return cls != null && cls.getEnclosingClass() != null;
    }
}
