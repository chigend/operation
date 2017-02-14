package com.cookabuy.entity.service.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午2:08
 */

public class PropPK implements Serializable {
    private Long cid;
    private Long pid;

    @Column(name = "cid", nullable = false)
    @Id
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Column(name = "pid", nullable = false)
    @Id
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PropPK propPK = (PropPK) o;

        if (cid != null ? !cid.equals(propPK.cid) : propPK.cid != null) return false;
        if (pid != null ? !pid.equals(propPK.pid) : propPK.pid != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        return result;
    }
}
