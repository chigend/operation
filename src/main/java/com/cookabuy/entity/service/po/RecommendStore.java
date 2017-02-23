package com.cookabuy.entity.service.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午1:57
 */

@Entity
@Table(name = "recommend_stores", schema = "public", catalog = "cookabuy_pc_int2")
public class RecommendStore {
    private UUID id;
    private UUID storeId;
    private String page;
    private Integer location;
    private Integer position;
    private String status;
    private String type;
    private String city;
    private Date createTime;
    private Date modifyTime;
    private String picUrl;

    @Id
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Column(name = "id", nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "store_id", nullable = true)
    @Type(type = "org.hibernate.type.PostgresUUIDType")
    public UUID getStoreId() {
        return storeId;
    }

    public void setStoreId(UUID storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "page", nullable = true)
    public String getPage() {
        return page;
    }

    public void setPage(String page) {
        this.page = page;
    }

    @Basic
    @Column(name = "location", nullable = true)
    public Integer getLocation() {
        return location;
    }

    public void setLocation(Integer location) {
        this.location = location;
    }

    @Basic
    @Column(name = "position", nullable = true)
    public Integer getPosition() {
        return position;
    }

    public void setPosition(Integer position) {
        this.position = position;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 8)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 8)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "city", nullable = true, length = 16)
    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Basic
    @Column(name = "create_time", nullable = false)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "modify_time", nullable = false)
    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendStore store = (RecommendStore) o;

        if (id != null ? !id.equals(store.id) : store.id != null) return false;
        if (storeId != null ? !storeId.equals(store.storeId) : store.storeId != null) return false;
        if (page != null ? !page.equals(store.page) : store.page != null) return false;
        if (location != null ? !location.equals(store.location) : store.location != null) return false;
        if (position != null ? !position.equals(store.position) : store.position != null) return false;
        if (status != null ? !status.equals(store.status) : store.status != null) return false;
        if (type != null ? !type.equals(store.type) : store.type != null) return false;
        if (city != null ? !city.equals(store.city) : store.city != null) return false;
        if (createTime != null ? !createTime.equals(store.createTime) : store.createTime != null) return false;
        if (modifyTime != null ? !modifyTime.equals(store.modifyTime) : store.modifyTime != null) return false;
        return picUrl != null ? picUrl.equals(store.picUrl) : store.picUrl == null;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (page != null ? page.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (city != null ? city.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        return result;
    }
}
