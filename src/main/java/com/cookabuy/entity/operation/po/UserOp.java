package com.cookabuy.entity.operation.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2016-12-22-13:19
 */

@Entity
@Table(name = "user_op", schema = "public", catalog = "cooka_operation_dev")
@IdClass(UserOpPK.class)
public class UserOp {
    private Integer userId;
    private Integer opId;


    public UserOp(Integer userId, Integer opId) {
        this.userId = userId;
        this.opId = opId;
    }

    public UserOp() {
    }

    @Id
    @Column(name = "user_id")
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Id
    @Column(name = "op_id")
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

        UserOp userOp = (UserOp) o;

        if (userId != null ? !userId.equals(userOp.userId) : userOp.userId != null) return false;
        if (opId != null ? !opId.equals(userOp.opId) : userOp.opId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (opId != null ? opId.hashCode() : 0);
        return result;
    }
}
