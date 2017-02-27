package com.cookabuy.entity.service.po;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-24-上午9:25
 */

@Entity
@Table(name = "published_display_category", schema = "public", catalog = "cookabuy_pc_int2")
public class PublishedDisplayCategory {
    private UUID id;
    private UUID pid;
    private String name;
    private Integer weight;

    @Id
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pid")
    @Type(type = "pg-uuid")
    public UUID getPid() {
        return pid;
    }

    public void setPid(UUID pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "weight")
    public Integer getWeight() {
        return weight;
    }

    public void setWeight(Integer weight) {
        this.weight = weight;
    }

}
