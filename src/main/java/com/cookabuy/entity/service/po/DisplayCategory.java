package com.cookabuy.entity.service.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午1:15
 */
@Entity
@Table(name = "display_category", schema = "public", catalog = "cookabuy_pc_int2")
public class DisplayCategory {
    private UUID id;
    private UUID pid;
    private String name;

    @Id
    @Type(type = "pg-uuid")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @GeneratedValue(generator = "uuid2")
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
}
