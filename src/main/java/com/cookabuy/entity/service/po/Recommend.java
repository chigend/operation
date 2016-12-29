package com.cookabuy.entity.service.po;

import javax.persistence.*;
import java.util.Date;

/**
 * 2016/12/8
 */
@Entity
@Table(name = "recommends", schema = "public", catalog = "cookabuy_pc_dev")
public class Recommend {
    private Integer id;
    private Long itemId;
    private String location;
    private String type;
    private String status;
    private Date insertedAt;
    private Date updatedAt;
    private String pageName;
    private Integer position;
    private String picUrl;

    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Basic
    @Column(name = "item_id")
    public Long getItemId() {
        return itemId;
    }

    public void setItemId(Long itemId) {
        this.itemId = itemId;
    }

    @Basic
    @Column(name = "location")
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "status")
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "inserted_at")
    public Date getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Date insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Date getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Date updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "page_name")
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Basic
    @Column(name = "position")
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Recommend recommend = (Recommend) o;

        if (id != null ? !id.equals(recommend.id) : recommend.id != null) return false;
        if (itemId != null ? !itemId.equals(recommend.itemId) : recommend.itemId != null) return false;
        if (location != null ? !location.equals(recommend.location) : recommend.location != null) return false;
        if (type != null ? !type.equals(recommend.type) : recommend.type != null) return false;
        if (status != null ? !status.equals(recommend.status) : recommend.status != null) return false;
        if (insertedAt != null ? !insertedAt.equals(recommend.insertedAt) : recommend.insertedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(recommend.updatedAt) : recommend.updatedAt != null) return false;
        if (pageName != null ? !pageName.equals(recommend.pageName) : recommend.pageName != null) return false;
        if (position != null ? !position.equals(recommend.position) : recommend.position != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (itemId != null ? itemId.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (pageName != null ? pageName.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        return result;
    }
}
