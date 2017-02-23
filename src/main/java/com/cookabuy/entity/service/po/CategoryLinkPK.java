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
    private Integer cid;

    @Type(type="pg-uuid")
    public UUID getDisplayId() {
        return displayId;
    }

    public void setDisplayId(UUID displayId) {
        this.displayId = displayId;
    }

    public Integer getCid() {
        return cid;
    }

    public void setCid(Integer cid) {
        this.cid = cid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CategoryLinkPK that = (CategoryLinkPK) o;

        if (displayId != null ? !displayId.equals(that.displayId) : that.displayId != null) return false;
        return cid != null ? cid.equals(that.cid) : that.cid == null;
    }

    @Override
    public int hashCode() {
        int result = displayId != null ? displayId.hashCode() : 0;
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        return result;
    }
}
