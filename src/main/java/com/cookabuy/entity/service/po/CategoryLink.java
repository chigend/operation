package com.cookabuy.entity.service.po;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:14
 */
@Entity
@IdClass(CategoryLinkPK.class)
@Table(name = "category_link", schema = "public", catalog = "cookabuy_pc_int2")
public class CategoryLink {
    private UUID displayId;
    private UUID cid;
    private String alias;

    public CategoryLink() {
    }

    public CategoryLink(UUID displayId, UUID cid, String alias) {
        this.displayId = displayId;
        this.cid = cid;
        this.alias = alias;
    }

    @Id
    @Column(name = "display_id")
    @Type(type = "pg-uuid")
    public UUID getDisplayId() {
        return displayId;
    }

    public void setDisplayId(UUID displayId) {
        this.displayId = displayId;
    }

    @Id
    @Column(name = "cid")
    @Type(type = "pg-uuid")
    public UUID getCid() {
        return cid;
    }

    public void setCid(UUID cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "alias")
    public String getAlias() {
        return alias;
    }

    public void setAlias(String alias) {
        this.alias = alias;
    }
}
