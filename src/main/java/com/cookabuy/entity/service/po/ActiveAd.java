package com.cookabuy.entity.service.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2017-02-13-下午4:20
 */

@Entity
@Table(name = "active_ad", schema = "public", catalog = "cookabuy_pc_int")
public class ActiveAd {
    private Integer id;

    public ActiveAd() {
    }

    public ActiveAd(Integer id) {
        this.id = id;
    }

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
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

        if (id != null ? !id.equals(activeAd.id) : activeAd.id != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id != null ? id.hashCode() : 0;
    }
}
