package com.cookabuy.entity.service.po;

import javax.persistence.*;
import java.util.Date;

/**
 * @author yejinbiao
 * @create 2017-02-13-下午5:04
 */

@Entity
@Table(name = "publish_log", schema = "public", catalog = "cookabuy_pc_int")
public class PublishLog {
    private Integer id;
    private String type;
    private Date publishTime;

    public PublishLog() {
    }

    public PublishLog(String type, Date publishTime) {
        this.type = type;
        this.publishTime = publishTime;
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
    @Column(name = "type")
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "publish_time")
    public Date getPublishTime() {
        return publishTime;
    }

    public void setPublishTime(Date publishTime) {
        this.publishTime = publishTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PublishLog that = (PublishLog) o;

        if (id != null ? !id.equals(that.id) : that.id != null) return false;
        if (type != null ? !type.equals(that.type) : that.type != null) return false;
        if (publishTime != null ? !publishTime.equals(that.publishTime) : that.publishTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (publishTime != null ? publishTime.hashCode() : 0);
        return result;
    }
}
