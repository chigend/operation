package com.cookabuy.entity.service.po;

import org.hibernate.annotations.Type;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午1:53
 */

public class ItemPK implements Serializable {
    private UUID id;
    private Long numIid;

    public ItemPK(UUID id, Long numIid) {
        this.id = id;
        this.numIid = numIid;
    }

    @Column(name = "id", nullable = false)
    @Id
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Column(name = "num_iid", nullable = false)
    @Id
    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        ItemPK itemPK = (ItemPK) o;

        if (id != null ? !id.equals(itemPK.id) : itemPK.id != null) return false;
        if (numIid != null ? !numIid.equals(itemPK.numIid) : itemPK.numIid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numIid != null ? numIid.hashCode() : 0);
        return result;
    }
}
