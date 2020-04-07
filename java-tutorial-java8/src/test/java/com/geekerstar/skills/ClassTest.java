package com.geekerstar.skills;

import org.junit.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author geekerstar
 * @date 2020/4/7 15:02
 * @description
 */
public class ClassTest {

    @Test
    public void getAllInterfaces_shouldFindAllInterfacesImplementedByAClass() {
        final List<Class<?>> list = ClassSnippets.getAllInterfaces(Class2.class);
        assertThat(list).hasSize(6);
        assertThat(list).containsExactly(I2.class, I3.class, I4.class, I5.class, I6.class, I1.class);
    }

    private static class Class1 implements I2, I1, I5 {
        // empty
    }

    private static class Class2 extends Class1 implements I2, I3 {
        // empty
    }

    private interface I1 {
        // empty
    }

    private interface I2 {
        // empty
    }

    private interface I3 extends I4, I5 {
        // empty
    }

    private interface I4 {
        // empty
    }

    private interface I5 extends I6 {
        // empty
    }

    private interface I6 {
        // empty
    }

}
