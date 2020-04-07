package com.geekerstar.skills;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author geekerstar
 * @date 2020/4/7 14:59
 * @description
 */
public class IOTest {

    @Test
    public void getCurrentWorkingDirectoryPath_test() throws Exception {
        assertThat(IOSnippets.getCurrentWorkingDirectoryPath()).isNotBlank();
    }
}
