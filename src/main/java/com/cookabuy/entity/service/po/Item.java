package com.cookabuy.entity.service.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.sql.Timestamp;

/**
 * @author yejinbiao
 * @create 2017-02-14-下午1:53
 */

@Entity
@Table(name = "tb_item", schema = "public", catalog = "cookabuy_pc_int2")
@IdClass(ItemPK.class)
public class Item {
    private String id;
    private Long numIid;
    private Long sid;
    private String storeId;
    private String detailUrl;
    private String h5DetailUrl;
    private String title;
    private String picUrl;
    private String nick;
    private String type;
    private String desc;
    private String h5Desc;
    private String props;
    private String propsName;
    private String promotedService;
    private BigInteger auctionPoint;
    private String propertyAlias;
    private String isXinpin;
    private Integer subStock;
    private Long cid;
    private Long rcid;
    private String sellerCids;
    private String inputPids;
    private String inputStr;
    private Integer num;
    private Integer validThru;
    private Long listTime;
    private Timestamp delistTime;
    private BigDecimal price;
    private BigDecimal tbPrice;
    private BigDecimal postFee;
    private BigDecimal expressFee;
    private BigDecimal emsFee;
    private Boolean hasDiscount;
    private String freightPayer;
    private Boolean hasInvoice;
    private Boolean hasShowcase;
    private String increment;
    private String approveStatus;
    private Long postageId;
    private String isTaobao;
    private String isTiming;
    private String secondKill;
    private String violation;
    private String sellPromise;
    private String shopName;
    private Boolean deleted;
    private Boolean valid;
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

    @Id
    @Column(name = "num_iid", nullable = false)
    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    @Basic
    @Column(name = "sid", nullable = true)
    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
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
    @Column(name = "detail_url", nullable = true, length = 120)
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Basic
    @Column(name = "h5_detail_url", nullable = true, length = 120)
    public String getH5DetailUrl() {
        return h5DetailUrl;
    }

    public void setH5DetailUrl(String h5DetailUrl) {
        this.h5DetailUrl = h5DetailUrl;
    }

    @Basic
    @Column(name = "title", nullable = true, length = 62)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "pic_url", nullable = true, length = 120)
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "nick", nullable = true, length = 32)
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
    }

    @Basic
    @Column(name = "type", nullable = true, length = 12)
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    @Basic
    @Column(name = "desc", nullable = true, length = -1)
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "h5_desc", nullable = true, length = -1)
    public String getH5Desc() {
        return h5Desc;
    }

    public void setH5Desc(String h5Desc) {
        this.h5Desc = h5Desc;
    }

    @Basic
    @Column(name = "props", nullable = true, length = 512)
    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    @Basic
    @Column(name = "props_name", nullable = true, length = 512)
    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    @Basic
    @Column(name = "promoted_service", nullable = true, length = 6)
    public String getPromotedService() {
        return promotedService;
    }

    public void setPromotedService(String promotedService) {
        this.promotedService = promotedService;
    }

    @Basic
    @Column(name = "auction_point", nullable = true, precision = 0)
    public BigInteger getAuctionPoint() {
        return auctionPoint;
    }

    public void setAuctionPoint(BigInteger auctionPoint) {
        this.auctionPoint = auctionPoint;
    }

    @Basic
    @Column(name = "property_alias", nullable = true, length = 128)
    public String getPropertyAlias() {
        return propertyAlias;
    }

    public void setPropertyAlias(String propertyAlias) {
        this.propertyAlias = propertyAlias;
    }

    @Basic
    @Column(name = "is_xinpin", nullable = true, length = 6)
    public String getIsXinpin() {
        return isXinpin;
    }

    public void setIsXinpin(String isXinpin) {
        this.isXinpin = isXinpin;
    }

    @Basic
    @Column(name = "sub_stock", nullable = true)
    public Integer getSubStock() {
        return subStock;
    }

    public void setSubStock(Integer subStock) {
        this.subStock = subStock;
    }

    @Basic
    @Column(name = "cid", nullable = true)
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "rcid", nullable = true)
    public Long getRcid() {
        return rcid;
    }

    public void setRcid(Long rcid) {
        this.rcid = rcid;
    }

    @Basic
    @Column(name = "seller_cids", nullable = true, length = 120)
    public String getSellerCids() {
        return sellerCids;
    }

    public void setSellerCids(String sellerCids) {
        this.sellerCids = sellerCids;
    }

    @Basic
    @Column(name = "input_pids", nullable = true, length = 120)
    public String getInputPids() {
        return inputPids;
    }

    public void setInputPids(String inputPids) {
        this.inputPids = inputPids;
    }

    @Basic
    @Column(name = "input_str", nullable = true, length = 120)
    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    @Basic
    @Column(name = "num", nullable = true)
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "valid_thru", nullable = true)
    public Integer getValidThru() {
        return validThru;
    }

    public void setValidThru(Integer validThru) {
        this.validThru = validThru;
    }

    @Basic
    @Column(name = "list_time", nullable = true)
    public Long getListTime() {
        return listTime;
    }

    public void setListTime(Long listTime) {
        this.listTime = listTime;
    }

    @Basic
    @Column(name = "delist_time", nullable = true)
    public Timestamp getDelistTime() {
        return delistTime;
    }

    public void setDelistTime(Timestamp delistTime) {
        this.delistTime = delistTime;
    }

    @Basic
    @Column(name = "price", nullable = true, precision = 0)
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "tb_price", nullable = true, precision = 0)
    public BigDecimal getTbPrice() {
        return tbPrice;
    }

    public void setTbPrice(BigDecimal tbPrice) {
        this.tbPrice = tbPrice;
    }

    @Basic
    @Column(name = "post_fee", nullable = true, precision = 0)
    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    @Basic
    @Column(name = "express_fee", nullable = true, precision = 0)
    public BigDecimal getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(BigDecimal expressFee) {
        this.expressFee = expressFee;
    }

    @Basic
    @Column(name = "ems_fee", nullable = true, precision = 0)
    public BigDecimal getEmsFee() {
        return emsFee;
    }

    public void setEmsFee(BigDecimal emsFee) {
        this.emsFee = emsFee;
    }

    @Basic
    @Column(name = "has_discount", nullable = true)
    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Basic
    @Column(name = "freight_payer", nullable = true, length = 6)
    public String getFreightPayer() {
        return freightPayer;
    }

    public void setFreightPayer(String freightPayer) {
        this.freightPayer = freightPayer;
    }

    @Basic
    @Column(name = "has_invoice", nullable = false)
    public Boolean getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(Boolean hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    @Basic
    @Column(name = "has_showcase", nullable = false)
    public Boolean getHasShowcase() {
        return hasShowcase;
    }

    public void setHasShowcase(Boolean hasShowcase) {
        this.hasShowcase = hasShowcase;
    }

    @Basic
    @Column(name = "increment", nullable = true, length = 18)
    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    @Basic
    @Column(name = "approve_status", nullable = true, length = 6)
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    @Basic
    @Column(name = "postage_id", nullable = true)
    public Long getPostageId() {
        return postageId;
    }

    public void setPostageId(Long postageId) {
        this.postageId = postageId;
    }

    @Basic
    @Column(name = "is_taobao", nullable = true, length = 6)
    public String getIsTaobao() {
        return isTaobao;
    }

    public void setIsTaobao(String isTaobao) {
        this.isTaobao = isTaobao;
    }

    @Basic
    @Column(name = "is_timing", nullable = true, length = 6)
    public String getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(String isTiming) {
        this.isTiming = isTiming;
    }

    @Basic
    @Column(name = "second_kill", nullable = true, length = 12)
    public String getSecondKill() {
        return secondKill;
    }

    public void setSecondKill(String secondKill) {
        this.secondKill = secondKill;
    }

    @Basic
    @Column(name = "violation", nullable = true, length = 6)
    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    @Basic
    @Column(name = "sell_promise", nullable = true, length = 6)
    public String getSellPromise() {
        return sellPromise;
    }

    public void setSellPromise(String sellPromise) {
        this.sellPromise = sellPromise;
    }

    @Basic
    @Column(name = "shop_name", nullable = true, length = 16)
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Basic
    @Column(name = "deleted", nullable = true)
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "valid", nullable = true)
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
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

        Item item = (Item) o;

        if (id != null ? !id.equals(item.id) : item.id != null) return false;
        if (numIid != null ? !numIid.equals(item.numIid) : item.numIid != null) return false;
        if (sid != null ? !sid.equals(item.sid) : item.sid != null) return false;
        if (storeId != null ? !storeId.equals(item.storeId) : item.storeId != null) return false;
        if (detailUrl != null ? !detailUrl.equals(item.detailUrl) : item.detailUrl != null) return false;
        if (h5DetailUrl != null ? !h5DetailUrl.equals(item.h5DetailUrl) : item.h5DetailUrl != null) return false;
        if (title != null ? !title.equals(item.title) : item.title != null) return false;
        if (picUrl != null ? !picUrl.equals(item.picUrl) : item.picUrl != null) return false;
        if (nick != null ? !nick.equals(item.nick) : item.nick != null) return false;
        if (type != null ? !type.equals(item.type) : item.type != null) return false;
        if (desc != null ? !desc.equals(item.desc) : item.desc != null) return false;
        if (h5Desc != null ? !h5Desc.equals(item.h5Desc) : item.h5Desc != null) return false;
        if (props != null ? !props.equals(item.props) : item.props != null) return false;
        if (propsName != null ? !propsName.equals(item.propsName) : item.propsName != null) return false;
        if (promotedService != null ? !promotedService.equals(item.promotedService) : item.promotedService != null)
            return false;
        if (auctionPoint != null ? !auctionPoint.equals(item.auctionPoint) : item.auctionPoint != null) return false;
        if (propertyAlias != null ? !propertyAlias.equals(item.propertyAlias) : item.propertyAlias != null)
            return false;
        if (isXinpin != null ? !isXinpin.equals(item.isXinpin) : item.isXinpin != null) return false;
        if (subStock != null ? !subStock.equals(item.subStock) : item.subStock != null) return false;
        if (cid != null ? !cid.equals(item.cid) : item.cid != null) return false;
        if (rcid != null ? !rcid.equals(item.rcid) : item.rcid != null) return false;
        if (sellerCids != null ? !sellerCids.equals(item.sellerCids) : item.sellerCids != null) return false;
        if (inputPids != null ? !inputPids.equals(item.inputPids) : item.inputPids != null) return false;
        if (inputStr != null ? !inputStr.equals(item.inputStr) : item.inputStr != null) return false;
        if (num != null ? !num.equals(item.num) : item.num != null) return false;
        if (validThru != null ? !validThru.equals(item.validThru) : item.validThru != null) return false;
        if (listTime != null ? !listTime.equals(item.listTime) : item.listTime != null) return false;
        if (delistTime != null ? !delistTime.equals(item.delistTime) : item.delistTime != null) return false;
        if (price != null ? !price.equals(item.price) : item.price != null) return false;
        if (tbPrice != null ? !tbPrice.equals(item.tbPrice) : item.tbPrice != null) return false;
        if (postFee != null ? !postFee.equals(item.postFee) : item.postFee != null) return false;
        if (expressFee != null ? !expressFee.equals(item.expressFee) : item.expressFee != null) return false;
        if (emsFee != null ? !emsFee.equals(item.emsFee) : item.emsFee != null) return false;
        if (hasDiscount != null ? !hasDiscount.equals(item.hasDiscount) : item.hasDiscount != null) return false;
        if (freightPayer != null ? !freightPayer.equals(item.freightPayer) : item.freightPayer != null) return false;
        if (hasInvoice != null ? !hasInvoice.equals(item.hasInvoice) : item.hasInvoice != null) return false;
        if (hasShowcase != null ? !hasShowcase.equals(item.hasShowcase) : item.hasShowcase != null) return false;
        if (increment != null ? !increment.equals(item.increment) : item.increment != null) return false;
        if (approveStatus != null ? !approveStatus.equals(item.approveStatus) : item.approveStatus != null)
            return false;
        if (postageId != null ? !postageId.equals(item.postageId) : item.postageId != null) return false;
        if (isTaobao != null ? !isTaobao.equals(item.isTaobao) : item.isTaobao != null) return false;
        if (isTiming != null ? !isTiming.equals(item.isTiming) : item.isTiming != null) return false;
        if (secondKill != null ? !secondKill.equals(item.secondKill) : item.secondKill != null) return false;
        if (violation != null ? !violation.equals(item.violation) : item.violation != null) return false;
        if (sellPromise != null ? !sellPromise.equals(item.sellPromise) : item.sellPromise != null) return false;
        if (shopName != null ? !shopName.equals(item.shopName) : item.shopName != null) return false;
        if (deleted != null ? !deleted.equals(item.deleted) : item.deleted != null) return false;
        if (valid != null ? !valid.equals(item.valid) : item.valid != null) return false;
        if (insertedAt != null ? !insertedAt.equals(item.insertedAt) : item.insertedAt != null) return false;
        if (updatedAt != null ? !updatedAt.equals(item.updatedAt) : item.updatedAt != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id != null ? id.hashCode() : 0;
        result = 31 * result + (numIid != null ? numIid.hashCode() : 0);
        result = 31 * result + (sid != null ? sid.hashCode() : 0);
        result = 31 * result + (storeId != null ? storeId.hashCode() : 0);
        result = 31 * result + (detailUrl != null ? detailUrl.hashCode() : 0);
        result = 31 * result + (h5DetailUrl != null ? h5DetailUrl.hashCode() : 0);
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (picUrl != null ? picUrl.hashCode() : 0);
        result = 31 * result + (nick != null ? nick.hashCode() : 0);
        result = 31 * result + (type != null ? type.hashCode() : 0);
        result = 31 * result + (desc != null ? desc.hashCode() : 0);
        result = 31 * result + (h5Desc != null ? h5Desc.hashCode() : 0);
        result = 31 * result + (props != null ? props.hashCode() : 0);
        result = 31 * result + (propsName != null ? propsName.hashCode() : 0);
        result = 31 * result + (promotedService != null ? promotedService.hashCode() : 0);
        result = 31 * result + (auctionPoint != null ? auctionPoint.hashCode() : 0);
        result = 31 * result + (propertyAlias != null ? propertyAlias.hashCode() : 0);
        result = 31 * result + (isXinpin != null ? isXinpin.hashCode() : 0);
        result = 31 * result + (subStock != null ? subStock.hashCode() : 0);
        result = 31 * result + (cid != null ? cid.hashCode() : 0);
        result = 31 * result + (rcid != null ? rcid.hashCode() : 0);
        result = 31 * result + (sellerCids != null ? sellerCids.hashCode() : 0);
        result = 31 * result + (inputPids != null ? inputPids.hashCode() : 0);
        result = 31 * result + (inputStr != null ? inputStr.hashCode() : 0);
        result = 31 * result + (num != null ? num.hashCode() : 0);
        result = 31 * result + (validThru != null ? validThru.hashCode() : 0);
        result = 31 * result + (listTime != null ? listTime.hashCode() : 0);
        result = 31 * result + (delistTime != null ? delistTime.hashCode() : 0);
        result = 31 * result + (price != null ? price.hashCode() : 0);
        result = 31 * result + (tbPrice != null ? tbPrice.hashCode() : 0);
        result = 31 * result + (postFee != null ? postFee.hashCode() : 0);
        result = 31 * result + (expressFee != null ? expressFee.hashCode() : 0);
        result = 31 * result + (emsFee != null ? emsFee.hashCode() : 0);
        result = 31 * result + (hasDiscount != null ? hasDiscount.hashCode() : 0);
        result = 31 * result + (freightPayer != null ? freightPayer.hashCode() : 0);
        result = 31 * result + (hasInvoice != null ? hasInvoice.hashCode() : 0);
        result = 31 * result + (hasShowcase != null ? hasShowcase.hashCode() : 0);
        result = 31 * result + (increment != null ? increment.hashCode() : 0);
        result = 31 * result + (approveStatus != null ? approveStatus.hashCode() : 0);
        result = 31 * result + (postageId != null ? postageId.hashCode() : 0);
        result = 31 * result + (isTaobao != null ? isTaobao.hashCode() : 0);
        result = 31 * result + (isTiming != null ? isTiming.hashCode() : 0);
        result = 31 * result + (secondKill != null ? secondKill.hashCode() : 0);
        result = 31 * result + (violation != null ? violation.hashCode() : 0);
        result = 31 * result + (sellPromise != null ? sellPromise.hashCode() : 0);
        result = 31 * result + (shopName != null ? shopName.hashCode() : 0);
        result = 31 * result + (deleted != null ? deleted.hashCode() : 0);
        result = 31 * result + (valid != null ? valid.hashCode() : 0);
        result = 31 * result + (insertedAt != null ? insertedAt.hashCode() : 0);
        result = 31 * result + (updatedAt != null ? updatedAt.hashCode() : 0);
        return result;
    }
}
