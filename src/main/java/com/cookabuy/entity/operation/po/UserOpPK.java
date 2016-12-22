package com.cookabuy.entity.operation.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yejinbiao
 * @create 2016-12-22-13:19
 */

public class UserOpPK implements Serializable {
    private Integer userId;
    private Integer opId;

    @Column(name = "user_id")
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "op_id")
    @Id
    public Integer getOpId() {
        return opId;
    }

    public void setOpId(Integer opId) {
        this.opId = opId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserOpPK userOpPK = (UserOpPK) o;

        if (userId != null ? !userId.equals(userOpPK.userId) : userOpPK.userId != null) return false;
        if (opId != null ? !opId.equals(userOpPK.opId) : userOpPK.opId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (opId != null ? opId.hashCode() : 0);
        return result;
    }
}
