package com.cookabuy.entity.operation.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午2:56
 */

@Entity
@Table(name = "user_permission", schema = "public", catalog = "cooka_operation_dev")
@IdClass(UserPermissionPK.class)
public class UserPermission {
    private Integer userId;
    private Integer permissionId;


    public UserPermission() {
    }

    public UserPermission(Integer userId, Integer permissionId) {
        this.userId = userId;
        this.permissionId = permissionId;
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
    @Column(name = "permission_id")
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserPermission that = (UserPermission) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }
}
