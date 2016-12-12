package com.cookabuy.entity.operation.po;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * 2016/12/12
 */
@Entity
public class Menu {
    private String name;
    private Integer id;
    private Integer parentId;
    private Integer permissionId;

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Id
    @Column(name = "id")
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "parent_id")
    public Integer getParentId() {
        return parentId;
    }

    public void setParentId(Integer parentId) {
        this.parentId = parentId;
    }

    @Basic
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

        Menu menu = (Menu) o;

        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        if (id != null ? !id.equals(menu.id) : menu.id != null) return false;
        if (parentId != null ? !parentId.equals(menu.parentId) : menu.parentId != null) return false;
        if (permissionId != null ? !permissionId.equals(menu.permissionId) : menu.permissionId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (parentId != null ? parentId.hashCode() : 0);
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }
}
