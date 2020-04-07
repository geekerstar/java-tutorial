package com.geekerstar.skills;

import org.junit.Test;

import java.util.AbstractMap;
import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author geekerstar
 * @date 2020/4/7 15:02
 * @description
 */
public class EnumTest {

    enum Priority {
        High, Medium, Low
    }

    @Test
    public void getEnumMap_convert_enum_to_map() throws Exception {
        Map<String, Priority> map = EnumSnippets.getEnumMap(Priority.class);
        assertThat(map).hasSize(3);
        assertThat(map)
                .containsOnly(
                        new AbstractMap.SimpleEntry<>("High", Priority.High),
                        new AbstractMap.SimpleEntry<>("Medium", Priority.Medium),
                        new AbstractMap.SimpleEntry<>("Low", Priority.Low)
                );
    }
}
