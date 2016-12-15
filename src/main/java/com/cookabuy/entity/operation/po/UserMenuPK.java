package com.cookabuy.entity.operation.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午2:51
 */

public class UserMenuPK implements Serializable{
    private Integer userId;
    private Integer menuId;

    @Column(name = "user_id")
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Column(name = "menu_id")
    @Id
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        UserMenuPK that = (UserMenuPK) o;

        if (userId != null ? !userId.equals(that.userId) : that.userId != null) return false;
        return menuId != null ? menuId.equals(that.menuId) : that.menuId == null;

    }

    @Override
    public int hashCode() {
        int result = userId != null ? userId.hashCode() : 0;
        result = 31 * result + (menuId != null ? menuId.hashCode() : 0);
        return result;
    }
}
