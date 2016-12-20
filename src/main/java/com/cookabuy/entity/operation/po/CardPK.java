package com.cookabuy.entity.operation.po;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * @author yejinbiao
 * @create 2016-12-20-下午8:09
 */

public class CardPK implements Serializable {
    private Integer id;
    private Integer userId;

    @Column(name = "id")
    @Id
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(name = "user_id")
    @Id
    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CardPK cardPK = (CardPK) o;

        if (id != null ? !id.equals(cardPK.id) : cardPK.id != null) return false;
        if (userId != null ? !userId.equals(cardPK.userId) : cardPK.userId != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (userId != null ? userId.hashCode() : 0);
        return result;
    }
}
