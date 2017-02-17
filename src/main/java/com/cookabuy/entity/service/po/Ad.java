package com.cookabuy.entity.service.po;

import org.hibernate.annotations.GenericGenerator;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import java.util.Date;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-15-上午9:56
 */

@Entity
public class Ad {
    private UUID adId;
    private String activityUrl;
    private Date createTime;
    private Boolean hidden = true;
    private String location;
    private String pageName;
    private String picUrl;
    private Integer position;
    private Boolean deleted = false;
    private Date modifyTime;

    @Id
    @Column(name = "ad_id", nullable = false)
    @GeneratedValue(generator = "uuid2")
    @GenericGenerator(name = "uuid2", strategy = "uuid2")
    @Type(type = "pg-uuid")
    public UUID getAdId() {
        return adId;
    }

    public void setAdId(UUID adId) {
        this.adId = adId;
    }

    @Basic
    @Column(name = "activity_url", nullable = true, length = 255)
    public String getActivityUrl() {
        return activityUrl;
    }

    public void setActivityUrl(String activityUrl) {
        this.activityUrl = activityUrl;
    }

    @Basic
    @Column(name = "create_time", nullable = true)
    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    @Basic
    @Column(name = "is_hidden",nullable = false)
    public boolean isHidden() {
        return hidden;
    }

    public void setHidden(boolean hidden) {
        this.hidden = hidden;
    }

    @Basic
    @Column(name = "location", nullable = true, length = 255)
    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    @Basic
    @Column(name = "page_name", nullable = true, length = 255)
    public String getPageName() {
        return pageName;
    }

    public void setPageName(String pageName) {
        this.pageName = pageName;
    }

    @Basic
    @Column(name = "pic_url", nullable = true, length = 255)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
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
    @Column(name = "deleted", nullable = false)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }


    @Basic
    @Column(name = "modify_time", nullable = true)
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

        Ad ad = (Ad) o;

        if (adId != null ? !adId.equals(ad.adId) : ad.adId != null) return false;
        if (activityUrl != null ? !activityUrl.equals(ad.activityUrl) : ad.activityUrl != null) return false;
        if (createTime != null ? !createTime.equals(ad.createTime) : ad.createTime != null) return false;
        if (hidden != null ? !hidden.equals(ad.hidden) : ad.hidden != null) return false;
        if (location != null ? !location.equals(ad.location) : ad.location != null) return false;
        if (pageName != null ? !pageName.equals(ad.pageName) : ad.pageName != null) return false;
        if (picUrl != null ? !picUrl.equals(ad.picUrl) : ad.picUrl != null) return false;
        if (position != null ? !position.equals(ad.position) : ad.position != null) return false;
        if (deleted != null ? !deleted.equals(ad.deleted) : ad.deleted != null) return false;
        if (modifyTime != null ? !modifyTime.equals(ad.modifyTime) : ad.modifyTime != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = adId != null ? adId.hashCode() : 0;
        result = 31 * result + (activityUrl != null ? activityUrl.hashCode() : 0);
        result = 31 * result + (createTime != null ? createTime.hashCode() : 0);
        result = 31 * result + (hidden != null ? hidden.hashCode() : 0);
        result = 31 * result + (location != null ? location.hashCode() : 0);
        result = 31 * result + (pageName != null ? pageName.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        result = 31 * result + (position != null ? position.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (modifyTime != null ? modifyTime.hashCode() : 0);
        return result;
    }
}
