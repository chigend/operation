package com.cookabuy.entity.service.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2017-02-15-上午9:55
 */

@Entity
@Table(name = "recommend_category", schema = "public", catalog = "cookabuy_pc_int2")
public class RecommendCategory {
    private String pageName;
    private String categoryName;
    private String categoryValue;
    private Integer id;

    @Basic
    @Column(name = "page_name", nullable = true, length = -1)
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Basic
    @Column(name = "category_name", nullable = true, length = -1)
    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    @Basic
    @Column(name = "category_value", nullable = true, length = -1)
    public String getCategoryValue() {
        return categoryValue;
    }

    public void setCategoryValue(String categoryValue) {
        this.categoryValue = categoryValue;
    }

    @Id
    @Column(name = "id", nullable = false)
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

        RecommendCategory that = (RecommendCategory) o;

        if (pageName != null ? !pageName.equals(that.pageName) : that.pageName != null) return false;
        if (categoryName != null ? !categoryName.equals(that.categoryName) : that.categoryName != null) return false;
        if (categoryValue != null ? !categoryValue.equals(that.categoryValue) : that.categoryValue != null)
            return false;
        if (id != null ? !id.equals(that.id) : that.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = pageName != null ? pageName.hashCode() : 0;
        result = 31 * result + (categoryName != null ? categoryName.hashCode() : 0);
        result = 31 * result + (categoryValue != null ? categoryValue.hashCode() : 0);
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
