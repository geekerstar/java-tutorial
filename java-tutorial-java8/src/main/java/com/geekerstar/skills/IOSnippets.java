package com.geekerstar.skills;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import java.nio.file.FileSystems;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * @author geekerstar
 * @date 2020/4/7 10:44
 * @description
 */
public class IOSnippets {

    /**
     * 将InputStream转换为字符串。
     *
     * @param in
     * @return
     * @throws IOException
     */
    public static String convertInputStreamToString(final InputStream in) throws IOException {
        ByteArrayOutputStream result = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];
        int length;
        while ((length = in.read(buffer)) != -1) {
            result.write(buffer, 0, length);
        }
        return result.toString(StandardCharsets.UTF_8.name());
    }


    /**
     * 将文件内容读入字符串。
     *
     * @param path
     * @return
     * @throws IOException
     */
    public String readFileAsString(Path path) throws IOException {
        return new String(Files.readAllBytes(path));
    }

    /**
     * 获取当前工作目录。
     *
     * @return
     */
    public static String getCurrentWorkingDirectoryPath() {
        return FileSystems.getDefault().getPath("").toAbsolutePath().toString();
    }

    /**
     * 返回 java.io.tmpdir 系统属性的值。如果末尾没有分隔符，则追加分隔符。
     *
     * @return
     */
    public static String tmpDirName() {
        String tmpDirName = System.getProperty("java.io.tmpdir");
        if (!tmpDirName.endsWith(File.separator)) {
            tmpDirName += File.separator;
        }

        return tmpDirName;
    }
}
