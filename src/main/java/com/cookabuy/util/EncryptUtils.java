package com.cookabuy.util;

import org.apache.shiro.crypto.hash.Md5Hash;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午4:27
 */

public class EncryptUtils {
    public static String md5crypt(String source){
        Md5Hash hash = new Md5Hash(source);
        return hash.toString();
    }
}
