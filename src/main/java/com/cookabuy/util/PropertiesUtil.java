package com.cookabuy.util;

import java.io.IOException;
import java.util.Properties;

/**
 * Created by yejinbiao on 16/9/28.
 */
public class PropertiesUtil {

    private  static Properties properties;



    public static void loadProperties(String filename){
        if(filename != null){
            properties = new Properties();
            ClassLoader classLoader = Thread.currentThread().getContextClassLoader();
            try {
                properties.load(classLoader.getResourceAsStream(filename));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static String getValue(String key){
        if(key != null){
            return properties.getProperty(key);
        }
        return null;
    }

}
