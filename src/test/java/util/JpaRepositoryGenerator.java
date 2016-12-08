package util;

import lombok.extern.slf4j.Slf4j;

import java.io.*;
import java.util.Set;

/**
 * Created by yejinbiao on 2016/10/14.
 */
@Slf4j
public class JpaRepositoryGenerator {
    private static final String FILE_SUFFIX = "Repository.java";
     private String repositoryPath;
    private String replaceName;

    private String templateLocation;

    private Set<String> nameFilters ;

    public String getTemplateLocation() {
        return templateLocation;
    }

    public void setTemplateLocation(String templateLocation) {
        this.templateLocation = templateLocation;
    }

    public String getReplaceName() {
        return replaceName;
    }

    public void setReplaceName(String replaceName) {
        this.replaceName = replaceName;
    }
    public String getRepositoryPath() {
        return repositoryPath;
    }

    public void setRepositoryPath(String repositoryPath) {
        this.repositoryPath = repositoryPath;
    }

    public JpaRepositoryGenerator(){

    }

    public void filter(Set<String>filters){
        this.nameFilters = filters;
    }
    public void generate(String className) {
        if("Test".equals(className)){
            return;
        }
        String  repositoryName = repositoryPath.concat(className).concat(FILE_SUFFIX);
        if(isRepostioryExist(repositoryName) ){
            return;
        }
        try (BufferedReader br = new BufferedReader(new FileReader(this.templateLocation));
             BufferedWriter bw = new BufferedWriter(new FileWriter(repositoryName))) {
            String line = null;
            while ((line = br.readLine()) != null) {
                if (line.startsWith("/*") || line.startsWith(" *")) {
                    continue;
                }
                line = line.replace(replaceName, className);//将模版文件中的指定字符串替换成想要的字符串
                bw.write(line);
                bw.newLine();
            }
            log.info("generate repository {} successfully",repositoryName);
        } catch (IOException e) {
            System.out.print(e);
        }
    }
    private boolean isRepostioryExist(String fileName){
        return new File(fileName).exists();
    }
}
