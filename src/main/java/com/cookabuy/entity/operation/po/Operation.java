package com.cookabuy.entity.operation.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2016-12-22-13:14
 */

@Entity
@Table(name = "operation", schema = "public", catalog = "cooka_operation_dev")
public class Operation {
    private String name;
    private Integer id;
    private Integer menuId;
    private Integer permissionId;
    private boolean selected;

    @Transient
    public boolean isSelected() {
        return selected;
    }

    public void setSelected(boolean selected) {
        this.selected = selected;
    }
    @Basic
    @Column(name = "menu_id")
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

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

        Operation operation = (Operation) o;

        if (name != null ? !name.equals(operation.name) : operation.name != null) return false;
        if (id != null ? !id.equals(operation.id) : operation.id != null) return false;
        if (permissionId != null ? !permissionId.equals(operation.permissionId) : operation.permissionId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (permissionId != null ? permissionId.hashCode() : 0);
        return result;
    }
}
