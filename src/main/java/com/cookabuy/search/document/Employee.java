package com.cookabuy.search.document;

import lombok.Getter;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldIndex;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @author yejinbiao
 * @create 2016-12-15-上午10:01
 */
@Document(indexName = "facebook", type = "employee")
@Setter
@Getter
public class Employee {
    @Id
    private Integer id;

    @Field(type = FieldType.String,index = FieldIndex.analyzed)
    private String name;

    @Field(type = FieldType.Integer,index = FieldIndex.not_analyzed)
    private Integer age;

}
