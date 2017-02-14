package com.cookabuy.entity.service.po;

import javax.persistence.*;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午2:08
 */

@Entity
@Table(name = "tb_prop", schema = "public", catalog = "cookabuy_pc_int2")
@IdClass(PropPK.class)
public class Prop {
    private Long cid;
    private Long pid;
    private String name;
    private Long parentPid;
    private Long parentVid;
    private Boolean isKeyProp;
    private Boolean isSaleProp;
    private Boolean isColorProp;
    private Boolean isEnumProp;
    private Boolean isInputProp;
    private Boolean isItemProp;
    private String childPath;
    private Integer type;
    private String attrKeys;

    @Id
    @Column(name = "cid", nullable = false)
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Id
    @Column(name = "pid", nullable = false)
    public Long getPid() {
        return pid;
    }

    public void setPid(Long pid) {
        this.pid = pid;
    }

    @Basic
    @Column(name = "name", nullable = true, length = 16)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "parent_pid", nullable = true)
    public Long getParentPid() {
        return parentPid;
    }

    public void setParentPid(Long parentPid) {
        this.parentPid = parentPid;
    }

    @Basic
    @Column(name = "parent_vid", nullable = true)
    public Long getParentVid() {
        return parentVid;
    }

    public void setParentVid(Long parentVid) {
        this.parentVid = parentVid;
    }

    @Basic
    @Column(name = "is_key_prop", nullable = false)
    public Boolean getKeyProp() {
        return isKeyProp;
    }

    public void setKeyProp(Boolean keyProp) {
        isKeyProp = keyProp;
    }

    @Basic
    @Column(name = "is_sale_prop", nullable = false)
    public Boolean getSaleProp() {
        return isSaleProp;
    }

    public void setSaleProp(Boolean saleProp) {
        isSaleProp = saleProp;
    }

    @Basic
    @Column(name = "is_color_prop", nullable = false)
    public Boolean getColorProp() {
        return isColorProp;
    }

    public void setColorProp(Boolean colorProp) {
        isColorProp = colorProp;
    }

    @Basic
    @Column(name = "is_enum_prop", nullable = false)
    public Boolean getEnumProp() {
        return isEnumProp;
    }

    public void setEnumProp(Boolean enumProp) {
        isEnumProp = enumProp;
    }

    @Basic
    @Column(name = "is_input_prop", nullable = false)
    public Boolean getInputProp() {
        return isInputProp;
    }

    public void setInputProp(Boolean inputProp) {
        isInputProp = inputProp;
    }

    @Basic
    @Column(name = "is_item_prop", nullable = false)
    public Boolean getItemProp() {
        return isItemProp;
    }

    public void setItemProp(Boolean itemProp) {
        isItemProp = itemProp;
    }

    @Basic
    @Column(name = "child_path", nullable = true, length = 8)
    public String getChildPath() {
        return childPath;
    }

    public void setChildPath(String childPath) {
        this.childPath = childPath;
    }

    @Basic
    @Column(name = "type", nullable = true)
    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    @Basic
    @Column(name = "attr_keys", nullable = true, length = 16)
    public String getAttrKeys() {
        return attrKeys;
    }

    public void setAttrKeys(String attrKeys) {
        this.attrKeys = attrKeys;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Prop prop = (Prop) o;

        if (cid != null ? !cid.equals(prop.cid) : prop.cid != null) return false;
        if (pid != null ? !pid.equals(prop.pid) : prop.pid != null) return false;
        if (name != null ? !name.equals(prop.name) : prop.name != null) return false;
        if (parentPid != null ? !parentPid.equals(prop.parentPid) : prop.parentPid != null) return false;
        if (parentVid != null ? !parentVid.equals(prop.parentVid) : prop.parentVid != null) return false;
        if (isKeyProp != null ? !isKeyProp.equals(prop.isKeyProp) : prop.isKeyProp != null) return false;
        if (isSaleProp != null ? !isSaleProp.equals(prop.isSaleProp) : prop.isSaleProp != null) return false;
        if (isColorProp != null ? !isColorProp.equals(prop.isColorProp) : prop.isColorProp != null) return false;
        if (isEnumProp != null ? !isEnumProp.equals(prop.isEnumProp) : prop.isEnumProp != null) return false;
        if (isInputProp != null ? !isInputProp.equals(prop.isInputProp) : prop.isInputProp != null) return false;
        if (isItemProp != null ? !isItemProp.equals(prop.isItemProp) : prop.isItemProp != null) return false;
        if (childPath != null ? !childPath.equals(prop.childPath) : prop.childPath != null) return false;
        if (type != null ? !type.equals(prop.type) : prop.type != null) return false;
        if (attrKeys != null ? !attrKeys.equals(prop.attrKeys) : prop.attrKeys != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = cid != null ? cid.hashCode() : 0;
        result = 31 * result + (pid != null ? pid.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (parentPid != null ? parentPid.hashCode() : 0);
        result = 31 * result + (parentVid != null ? parentVid.hashCode() : 0);
        result = 31 * result + (isKeyProp != null ? isKeyProp.hashCode() : 0);
        result = 31 * result + (isSaleProp != null ? isSaleProp.hashCode() : 0);
        result = 31 * result + (isColorProp != null ? isColorProp.hashCode() : 0);
        result = 31 * result + (isEnumProp != null ? isEnumProp.hashCode() : 0);
        result = 31 * result + (isInputProp != null ? isInputProp.hashCode() : 0);
        result = 31 * result + (isItemProp != null ? isItemProp.hashCode() : 0);
        result = 31 * result + (childPath != null ? childPath.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (attrKeys != null ? attrKeys.hashCode() : 0);
        return result;
    }
}
