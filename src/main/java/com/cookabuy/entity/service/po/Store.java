package com.cookabuy.entity.service.po;

import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午1:57
 */

@Entity
@Table(name = "stores", schema = "public", catalog = "cookabuy_pc_int2")
public class Store {
    private UUID id;
    private String storeName;
    private String storeNumber;
    private String storeLogo;
    private String cat;
    private String cates;
    private String market;
    private String floor;
    private String location;
    private String originArea;
    private String taobaoUrl;
    private String mobile;
    private String mobile2;
    private String qq;
    private String reduceType;
    private String reduce;
    private String wechat;
    private String ww;
    private String status;
    private String fetchStatus;
    private String indexStatus;
    private String parseStatus;
    private Integer totalCount;
    private Integer newCount;
    private Timestamp insertedAt;
    private Timestamp updatedAt;

    @Id
    @Column(name = "id", nullable = false)
    @Type(type = "pg-uuid")
    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    @Basic
    @Column(name = "store_name", nullable = true, length = 64)
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Basic
    @Column(name = "store_number", nullable = true, length = 64)
    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    @Basic
    @Column(name = "store_logo", nullable = true, length = 255)
    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    @Basic
    @Column(name = "cat", nullable = true, length = 64)
    public String getCat() {
        return cat;
    }

    public void setCat(String cat) {
        this.cat = cat;
    }

    @Basic
    @Column(name = "cates", nullable = true, length = 128)
    public String getCates() {
        return cates;
    }

    public void setCates(String cates) {
        this.cates = cates;
    }

    @Basic
    @Column(name = "market", nullable = true, length = 64)
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Basic
    @Column(name = "floor", nullable = true, length = 32)
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 128)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "origin_area", nullable = true, length = 64)
    public String getOriginArea() {
        return originArea;
    }

    public void setOriginArea(String originArea) {
        this.originArea = originArea;
    }

    @Basic
    @Column(name = "taobao_url", nullable = true, length = 255)
    public String getTaobaoUrl() {
        return taobaoUrl;
    }

    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }

    @Basic
    @Column(name = "mobile", nullable = true, length = 32)
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "mobile2", nullable = true, length = 32)
    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    @Basic
    @Column(name = "qq", nullable = true, length = 64)
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "reduce_type", nullable = true, length = 16)
    public String getReduceType() {
        return reduceType;
    }

    public void setReduceType(String reduceType) {
        this.reduceType = reduceType;
    }

    @Basic
    @Column(name = "reduce", nullable = true, length = 16)
    public String getReduce() {
        return reduce;
    }

    public void setReduce(String reduce) {
        this.reduce = reduce;
    }

    @Basic
    @Column(name = "wechat", nullable = true, length = 32)
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "ww", nullable = true, length = 32)
    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
    }

    @Basic
    @Column(name = "status", nullable = true, length = 16)
    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Basic
    @Column(name = "fetch_status", nullable = true, length = 32)
    public String getFetchStatus() {
        return fetchStatus;
    }

    public void setFetchStatus(String fetchStatus) {
        this.fetchStatus = fetchStatus;
    }

    @Basic
    @Column(name = "index_status", nullable = true, length = 32)
    public String getIndexStatus() {
        return indexStatus;
    }

    public void setIndexStatus(String indexStatus) {
        this.indexStatus = indexStatus;
    }

    @Basic
    @Column(name = "parse_status", nullable = true, length = 32)
    public String getParseStatus() {
        return parseStatus;
    }

    public void setParseStatus(String parseStatus) {
        this.parseStatus = parseStatus;
    }

    @Basic
    @Column(name = "total_count", nullable = true)
    public Integer getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(Integer totalCount) {
        this.totalCount = totalCount;
    }

    @Basic
    @Column(name = "new_count", nullable = true)
    public Integer getNewCount() {
        return newCount;
    }

    public void setNewCount(Integer newCount) {
        this.newCount = newCount;
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

        Store store = (Store) o;

        if (id != null ? !id.equals(store.id) : store.id != null) return false;
        if (storeName != null ? !storeName.equals(store.storeName) : store.storeName != null) return false;
        if (storeNumber != null ? !storeNumber.equals(store.storeNumber) : store.storeNumber != null) return false;
        if (storeLogo != null ? !storeLogo.equals(store.storeLogo) : store.storeLogo != null) return false;
        if (cat != null ? !cat.equals(store.cat) : store.cat != null) return false;
        if (cates != null ? !cates.equals(store.cates) : store.cates != null) return false;
        if (market != null ? !market.equals(store.market) : store.market != null) return false;
        if (floor != null ? !floor.equals(store.floor) : store.floor != null) return false;
        if (location != null ? !location.equals(store.location) : store.location != null) return false;
        if (originArea != null ? !originArea.equals(store.originArea) : store.originArea != null) return false;
        if (taobaoUrl != null ? !taobaoUrl.equals(store.taobaoUrl) : store.taobaoUrl != null) return false;
        if (mobile != null ? !mobile.equals(store.mobile) : store.mobile != null) return false;
        if (mobile2 != null ? !mobile2.equals(store.mobile2) : store.mobile2 != null) return false;
        if (qq != null ? !qq.equals(store.qq) : store.qq != null) return false;
        if (reduceType != null ? !reduceType.equals(store.reduceType) : store.reduceType != null) return false;
        if (reduce != null ? !reduce.equals(store.reduce) : store.reduce != null) return false;
        if (wechat != null ? !wechat.equals(store.wechat) : store.wechat != null) return false;
        if (ww != null ? !ww.equals(store.ww) : store.ww != null) return false;
        if (status != null ? !status.equals(store.status) : store.status != null) return false;
        if (fetchStatus != null ? !fetchStatus.equals(store.fetchStatus) : store.fetchStatus != null) return false;
        if (indexStatus != null ? !indexStatus.equals(store.indexStatus) : store.indexStatus != null) return false;
        if (parseStatus != null ? !parseStatus.equals(store.parseStatus) : store.parseStatus != null) return false;
        if (totalCount != null ? !totalCount.equals(store.totalCount) : store.totalCount != null) return false;
        if (newCount != null ? !newCount.equals(store.newCount) : store.newCount != null) return false;
        if (insertedAt != null ? !insertedAt.equals(store.insertedAt) : store.insertedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(store.updatedAt) : store.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (storeNumber != null ? storeNumber.hashCode() : 0);
        result = 31 * result + (storeLogo != null ? storeLogo.hashCode() : 0);
        result = 31 * result + (cat != null ? cat.hashCode() : 0);
        result = 31 * result + (cates != null ? cates.hashCode() : 0);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        result = 31 * result + (floor != null ? floor.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (originArea != null ? originArea.hashCode() : 0);
        result = 31 * result + (taobaoUrl != null ? taobaoUrl.hashCode() : 0);
        result = 31 * result + (mobile != null ? mobile.hashCode() : 0);
        result = 31 * result + (mobile2 != null ? mobile2.hashCode() : 0);
        result = 31 * result + (qq != null ? qq.hashCode() : 0);
        result = 31 * result + (reduceType != null ? reduceType.hashCode() : 0);
        result = 31 * result + (reduce != null ? reduce.hashCode() : 0);
        result = 31 * result + (wechat != null ? wechat.hashCode() : 0);
        result = 31 * result + (ww != null ? ww.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (fetchStatus != null ? fetchStatus.hashCode() : 0);
        result = 31 * result + (indexStatus != null ? indexStatus.hashCode() : 0);
        result = 31 * result + (parseStatus != null ? parseStatus.hashCode() : 0);
        result = 31 * result + (totalCount != null ? totalCount.hashCode() : 0);
        result = 31 * result + (newCount != null ? newCount.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
