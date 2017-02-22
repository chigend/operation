package com.cookabuy.entity.service.po;

import org.hibernate.annotations.Type;

import java.io.Serializable;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-下午2:17
 */

public class CategoryLinkPK implements Serializable{
    private UUID displayId;
    private UUID cid;

    @Type(type="pg-uuid")
    public UUID getDisplayId() {
        return displayId;
    }

    public void setDisplayId(UUID displayId) {
        this.displayId = displayId;
    }

    @Type(type = "pg-uuid")
    public UUID getCid() {
        return cid;
    }

    public void setCid(UUID cid) {
        this.cid = cid;
    }
}
