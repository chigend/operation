package com.cookabuy.entity.operation.po;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-14-下午5:16
 */

@Entity
@Table(name = "operation_user", schema = "public", catalog = "cooka_operation_dev")
public class OperationUser {
    private String username;
    private String password;
    private Integer id;
    private String realName;
    private Date createTime;
    private boolean administrator;

//    private List<Menu> menus;

    private List<Permission> permissions;



    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "isAdministrator")
    public boolean isAdministrator() {
        return administrator;
    }

    public void setAdministrator(boolean administrator) {
        this.administrator = administrator;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "create_time")
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    public Integer getId() {
        return id;
    }


    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "real_name")
    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
    @JoinTable(name="user_permission",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "permission_id",referencedColumnName = "permission_id")})
    public List<Permission> getPermissions() {
        return permissions;
    }

    public void setPermissions(List<Permission> permissions) {
        this.permissions = permissions;
    }


//    @ManyToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER)
//    @JoinTable(name="user_menu",joinColumns = {@JoinColumn(name = "user_id",referencedColumnName = "id")},
//            inverseJoinColumns = {@JoinColumn(name = "menu_id",referencedColumnName = "id")})
//    public List<Menu> getMenus() {
//        return menus;
//    }
//
//    public void setMenus(List<Menu> menus) {
//        this.menus = menus;
//    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationUser that = (OperationUser) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (realName != null ? !realName.equals(that.realName) : that.realName != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (realName != null ? realName.hashCode() : 0);
        return result;
    }
}
