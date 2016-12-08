package com.cookabuy.entity.po;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * 2016/12/7
 */
@Entity
@Table(name = "stores", schema = "public", catalog = "cookabuy_pc_dev")
public class Store {
    private Long id;
    private String storeName;
    private String storeNumber;
    private String storeLogo;
    private String market;
    private Integer marketId;
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
    private Date insertedAt;
    private Date updatedAt;
    private Integer sellerId;
    
    private List<Item> items;


    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "store")
    public List<Item> getItems() {
        return items;
    }

    public void setItems(List<Item> items) {
        this.items = items;
    }

    @Id
    @Column(name = "id")
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Basic
    @Column(name = "store_name")
    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    @Basic
    @Column(name = "store_number")
    public String getStoreNumber() {
        return storeNumber;
    }

    public void setStoreNumber(String storeNumber) {
        this.storeNumber = storeNumber;
    }

    @Basic
    @Column(name = "store_logo")
    public String getStoreLogo() {
        return storeLogo;
    }

    public void setStoreLogo(String storeLogo) {
        this.storeLogo = storeLogo;
    }

    @Basic
    @Column(name = "market")
    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }

    @Basic
    @Column(name = "market_id")
    public Integer getMarketId() {
        return marketId;
    }

    public void setMarketId(Integer marketId) {
        this.marketId = marketId;
    }

    @Basic
    @Column(name = "floor")
    public String getFloor() {
        return floor;
    }

    public void setFloor(String floor) {
        this.floor = floor;
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
    @Column(name = "origin_area")
    public String getOriginArea() {
        return originArea;
    }

    public void setOriginArea(String originArea) {
        this.originArea = originArea;
    }

    @Basic
    @Column(name = "taobao_url")
    public String getTaobaoUrl() {
        return taobaoUrl;
    }

    public void setTaobaoUrl(String taobaoUrl) {
        this.taobaoUrl = taobaoUrl;
    }

    @Basic
    @Column(name = "mobile")
    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    @Basic
    @Column(name = "mobile2")
    public String getMobile2() {
        return mobile2;
    }

    public void setMobile2(String mobile2) {
        this.mobile2 = mobile2;
    }

    @Basic
    @Column(name = "qq")
    public String getQq() {
        return qq;
    }

    public void setQq(String qq) {
        this.qq = qq;
    }

    @Basic
    @Column(name = "reduce_type")
    public String getReduceType() {
        return reduceType;
    }

    public void setReduceType(String reduceType) {
        this.reduceType = reduceType;
    }

    @Basic
    @Column(name = "reduce")
    public String getReduce() {
        return reduce;
    }

    public void setReduce(String reduce) {
        this.reduce = reduce;
    }

    @Basic
    @Column(name = "wechat")
    public String getWechat() {
        return wechat;
    }

    public void setWechat(String wechat) {
        this.wechat = wechat;
    }

    @Basic
    @Column(name = "ww")
    public String getWw() {
        return ww;
    }

    public void setWw(String ww) {
        this.ww = ww;
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
    @Column(name = "fetch_status")
    public String getFetchStatus() {
        return fetchStatus;
    }

    public void setFetchStatus(String fetchStatus) {
        this.fetchStatus = fetchStatus;
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
    @Column(name = "seller_id")
    public Integer getSellerId() {
        return sellerId;
    }

    public void setSellerId(Integer sellerId) {
        this.sellerId = sellerId;
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
        if (market != null ? !market.equals(store.market) : store.market != null) return false;
        if (marketId != null ? !marketId.equals(store.marketId) : store.marketId != null) return false;
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
        if (insertedAt != null ? !insertedAt.equals(store.insertedAt) : store.insertedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(store.updatedAt) : store.updatedAt != null) return false;
        if (sellerId != null ? !sellerId.equals(store.sellerId) : store.sellerId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (storeName != null ? storeName.hashCode() : 0);
        result = 31 * result + (storeNumber != null ? storeNumber.hashCode() : 0);
        result = 31 * result + (storeLogo != null ? storeLogo.hashCode() : 0);
        result = 31 * result + (market != null ? market.hashCode() : 0);
        result = 31 * result + (marketId != null ? marketId.hashCode() : 0);
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
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        result = 31 * result + (sellerId != null ? sellerId.hashCode() : 0);
        return result;
    }
}
