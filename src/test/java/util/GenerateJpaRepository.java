package util;

import java.io.File;
import java.util.Arrays;
import java.util.Objects;


/**
 * 该类用来自动扫描entity包下的所有实体类并生成jpa repositoy
 */
public class GenerateJpaRepository {
    public static void main(String[] args) {
        String entityPath = "src/main/java/com/cookabuy/entity/tmp";
        String repositoryPath = "src/main/java/com/cookabuy/repository/";
        String templateLocation = "src/main/resources/TemplateRepository.txt";
        String templateName = "Template";
        File entities = new File(entityPath);
        JpaRepositoryGenerator generator = new JpaRepositoryGenerator();
        generator.setReplaceName("Template");
        generator.setRepositoryPath(repositoryPath);
        generator.setTemplateLocation(templateLocation);

        Arrays.stream(Objects.requireNonNull(entities.listFiles()))//使用stream将entity包下的所有类作为流处理
                .map(file -> file.getName().substring(0, file.getName().indexOf(".")))//去掉文件名的后缀
                .forEach(generator::generate);

    }
}
