package com.cookabuy.entity.service.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2017-02-15-上午10:46
 */

@Entity
@Table(name = "active_ad", schema = "public", catalog = "cookabuy_pc_int2")
public class ActiveAd {
    private Integer adId;
    private Integer id;

    public ActiveAd() {
    }

    public ActiveAd(Integer adId) {
        this.adId = adId;
    }

    @Basic
    @Column(name = "ad_id", nullable = false)
    public Integer getAdId() {
        return adId;
    }

    public void setAdId(Integer adId) {
        this.adId = adId;
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

        ActiveAd activeAd = (ActiveAd) o;

        if (adId != null ? !adId.equals(activeAd.adId) : activeAd.adId != null) return false;
        if (id != null ? !id.equals(activeAd.id) : activeAd.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adId != null ? adId.hashCode() : 0;
        result = 31 * result + (id != null ? id.hashCode() : 0);
        return result;
    }
}
