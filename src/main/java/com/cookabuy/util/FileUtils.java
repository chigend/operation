package com.cookabuy.util;

import java.util.Objects;

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

    /**
     * 根据已上传至cos上的文件的url路径来获取其在cos上的cosPath
     *e.g 传入http://test-1252811756.cosgz.myqcloud.com/ad/e8c55733-935e-40ab-8dc0-605091d210ed.jpg
     * 返回/ad/e8c55733-935e-40ab-8dc0-605091d210ed.jpg
     * @param fileUrl 上传至cos上的url路径
     * @return 返回文件的cosPath
     * @see com.cookabuy.thirdParty.cos.FileHelper#deleteFile(String, String) 调用了getCosPath方法获取了文件的cosPath
     */
    public static String getCosPath(String fileUrl){
        Objects.requireNonNull(fileUrl);
        String pattern = "com";
        int index = fileUrl.lastIndexOf(pattern);
        return fileUrl.substring(index+pattern.length());
    }
}
