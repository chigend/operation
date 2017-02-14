package com.cookabuy.entity.service.po;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午1:57
 */

@Entity
@Table(name = "recommend_stores", schema = "public", catalog = "cookabuy_pc_int2")
public class RecommendStore {
    private String id;
    private String storeId;
    private Integer page;
    private Integer location;
    private Integer position;
    private String status;
    private String type;
    private String city;
    private Timestamp insertedAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Basic
    @Column(name = "store_id", nullable = true)
    public String getStoreId() {
        return storeId;
    }

    public void setStoreId(String storeId) {
        this.storeId = storeId;
    }

    @Basic
    @Column(name = "page", nullable = true)
    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
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
    @Column(name = "inserted_at", nullable = false)
    public Timestamp getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Timestamp insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Basic
    @Column(name = "updated_at", nullable = false)
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RecommendStore that = (RecommendStore) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (storeId != null ? !storeId.equals(that.storeId) : that.storeId != null) return false;
        if (page != null ? !page.equals(that.page) : that.page != null) return false;
        if (location != null ? !location.equals(that.location) : that.location != null) return false;
        if (position != null ? !position.equals(that.position) : that.position != null) return false;
        if (status != null ? !status.equals(that.status) : that.status != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (city != null ? !city.equals(that.city) : that.city != null) return false;
        if (insertedAt != null ? !insertedAt.equals(that.insertedAt) : that.insertedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(that.updatedAt) : that.updatedAt != null) return false;

        return true;
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
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
