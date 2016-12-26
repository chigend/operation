package com.cookabuy.util;

/**
 * 文件相关处理工具类
 *
 * @author yejinbiao
 * @create 2016-12-23-16:53
 */

public class FileUtils {
    /**
     * @param srcFileName 源文件名
     * @return 文件后缀名 如果文件没有后缀 则返回""空字符串
     */
    public static String getSuffix(String srcFileName) {
        int index = srcFileName.lastIndexOf(".");
        return index < 0 ? "" : srcFileName.substring(index);
    }
}
