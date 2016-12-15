package com.cookabuy.entity.operation.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午2:47
 */

@Entity
@Table(name = "user_menu", schema = "public", catalog = "cooka_operation_dev")
@IdClass(UserMenuPK.class)
public class UserMenu {
    private Integer userId;
    private Integer menuId;

    public UserMenu() {
    }

    public UserMenu(Integer userId, Integer menuId) {
        this.userId = userId;
        this.menuId = menuId;
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
    @Column(name = "menu_id")
    public Integer getMenuId() {
        return menuId;
    }

    public void setMenuId(Integer menuId) {
        this.menuId = menuId;
    }
}
