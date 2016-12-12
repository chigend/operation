package com.cookabuy.entity.operation.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 2016/12/12
 */
@Entity
public class Permission {
    private Integer permissionId;
    private String permission;

    @Id
    @Column(name = "permission_id")
    public Integer getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(Integer permissionId) {
        this.permissionId = permissionId;
    }

    @Basic
    @Column(name = "permission")
    public String getPermission() {
        return permission;
    }

    public void setPermission(String permission) {
        this.permission = permission;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Permission that = (Permission) o;

        if (permissionId != null ? !permissionId.equals(that.permissionId) : that.permissionId != null) return false;
        if (permission != null ? !permission.equals(that.permission) : that.permission != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = permissionId != null ? permissionId.hashCode() : 0;
        result = 31 * result + (permission != null ? permission.hashCode() : 0);
        return result;
    }
}
