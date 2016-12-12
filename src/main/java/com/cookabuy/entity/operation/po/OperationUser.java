package com.cookabuy.entity.operation.po;

import javax.persistence.*;

/**
 * 2016/12/12
 */
@Entity
@Table(name = "operation_user", schema = "public", catalog = "cooka_operation_dev")
public class OperationUser {
    private String username;
    private String password;
    private Integer id;

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
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
    @Column(name = "id")
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        OperationUser that = (OperationUser) o;

        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
