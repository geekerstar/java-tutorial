package com.geekerstar.skills;

import java.util.Arrays;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

/**
 * @author geekerstar
 * @date 2020/4/7 10:48
 * @description
 */
public class EnumSnippets {

    /**
     * 将枚举转换为 Map，其中 key 是枚举名，value 是枚举本身
     *
     * @param enumClass
     * @param <E>
     * @return
     */
    public static <E extends Enum<E>> Map<String, E> getEnumMap(final Class<E> enumClass) {
        return Arrays.stream(enumClass.getEnumConstants())
                .collect(Collectors.toMap(Enum::name, Function.identity()));
    }
}
