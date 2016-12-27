package com.cookabuy.entity.operation.po;

import lombok.ToString;

import javax.persistence.*;
import java.util.List;

/**
 * @author yejinbiao
 * @create 2016-12-13-下午2:56
 */

@Entity
@Table(name = "menu", schema = "public", catalog = "cooka_operation_dev")
@ToString
public class Menu {
    private String name;
    private Integer id;
    //菜单的类目
    private String category;

    private List<Operation> operations;


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
    @Column(name = "category")
    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    @OneToMany(fetch = FetchType.EAGER)
    @JoinColumn(name="menu_id")
    public List<Operation> getOperations() {
        return operations;
    }

    public void setOperations(List<Operation> operations) {
        this.operations = operations;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Menu menu = (Menu) o;

        if (name != null ? !name.equals(menu.name) : menu.name != null) return false;
        if (id != null ? !id.equals(menu.id) : menu.id != null) return false;
        if (category != null ? !category.equals(menu.category) : menu.category != null) return false;
        return operations != null ? operations.equals(menu.operations) : menu.operations == null;

    }

    @Override
    public int hashCode() {
        int result = name != null ? name.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        result = 31 * result + (category != null ? category.hashCode() : 0);
        return result;
    }
}
