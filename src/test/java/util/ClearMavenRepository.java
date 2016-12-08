package util;

import java.io.File;

/**
 *该类用来解决pom文件报红出错问题，原因是使用maven下载jar包时，下载到一半而发生中断的话 会产生以lastUpdated为后缀的文件
 * 将此类文件删除即可
 */
public class ClearMavenRepository {
    public static void main(String [] args){
        String path = "/users/yejinbiao/.m2/repository";
        File file = new File(path);
        deleteFile(file);
    }
    public static void deleteFile(File file){
        if(file.isFile()){
            if(file.getName().endsWith("lastUpdated")){
                System.out.println("delete:" + file.getName());
                file.delete();
            }
            return;
        }
        File [] childs = file.listFiles();
        for(File child:childs){
            deleteFile(child);
        }
    }
}
