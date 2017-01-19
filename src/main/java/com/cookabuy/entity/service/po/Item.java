package com.cookabuy.entity.service.po;

import javax.persistence.*;
import java.math.BigDecimal;
import java.sql.Timestamp;

/**
 * @author yejinbiao
 * @create 2016-12-16-下午4:52
 */

@Entity
@Table(name = "tb_item", schema = "public", catalog = "cookabuy_pc_int")
public class Item {
    private Long numIid;
    private Long sid;
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
    private BigDecimal auctionPoint;
    private String propertyAlias;
    private String isXinpin;
    private Integer subStock;
    private Long cid;
    private String sellerCids;
    private String inputPids;
    private String inputStr;
    private Integer num;
    private Integer validThru;
    private Timestamp delistTime;
    private BigDecimal price;
    private BigDecimal postFee;
    private BigDecimal expressFee;
    private BigDecimal emsFee;
    private String freightPayer;
    private String hasInvoice;
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
    private Timestamp insertedAt;
    private Timestamp updatedAt;
    private Boolean hasShowcase;
    private Boolean valid;
    private Boolean hasDiscount;
    private Long listTime;
    private String wapDesc;
    private String wapDetailUrl;

    private Store store;

    @ManyToOne
    @JoinColumn(name="store_id")
    public Store getStore() {
        return store;
    }

    public void setStore(Store store) {
        this.store = store;
    }

    @Id
    @Column(name = "num_iid")
    public Long getNumIid() {
        return numIid;
    }

    public void setNumIid(Long numIid) {
        this.numIid = numIid;
    }

    @Basic
    @Column(name = "sid")
    public Long getSid() {
        return sid;
    }

    public void setSid(Long sid) {
        this.sid = sid;
    }

    @Basic
    @Column(name = "detail_url")
    public String getDetailUrl() {
        return detailUrl;
    }

    public void setDetailUrl(String detailUrl) {
        this.detailUrl = detailUrl;
    }

    @Basic
    @Column(name = "h5_detail_url")
    public String getH5DetailUrl() {
        return h5DetailUrl;
    }

    public void setH5DetailUrl(String h5DetailUrl) {
        this.h5DetailUrl = h5DetailUrl;
    }

    @Basic
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "pic_url")
    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    @Basic
    @Column(name = "nick")
    public String getNick() {
        return nick;
    }

    public void setNick(String nick) {
        this.nick = nick;
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
    @Column(name = "desc")
    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Basic
    @Column(name = "h5_desc")
    public String getH5Desc() {
        return h5Desc;
    }

    public void setH5Desc(String h5Desc) {
        this.h5Desc = h5Desc;
    }

    @Basic
    @Column(name = "props")
    public String getProps() {
        return props;
    }

    public void setProps(String props) {
        this.props = props;
    }

    @Basic
    @Column(name = "props_name")
    public String getPropsName() {
        return propsName;
    }

    public void setPropsName(String propsName) {
        this.propsName = propsName;
    }

    @Basic
    @Column(name = "promoted_service")
    public String getPromotedService() {
        return promotedService;
    }

    public void setPromotedService(String promotedService) {
        this.promotedService = promotedService;
    }

    @Basic
    @Column(name = "auction_point")
    public BigDecimal getAuctionPoint() {
        return auctionPoint;
    }

    public void setAuctionPoint(BigDecimal auctionPoint) {
        this.auctionPoint = auctionPoint;
    }

    @Basic
    @Column(name = "property_alias")
    public String getPropertyAlias() {
        return propertyAlias;
    }

    public void setPropertyAlias(String propertyAlias) {
        this.propertyAlias = propertyAlias;
    }

    @Basic
    @Column(name = "is_xinpin")
    public String getIsXinpin() {
        return isXinpin;
    }

    public void setIsXinpin(String isXinpin) {
        this.isXinpin = isXinpin;
    }

    @Basic
    @Column(name = "sub_stock")
    public Integer getSubStock() {
        return subStock;
    }

    public void setSubStock(Integer subStock) {
        this.subStock = subStock;
    }

    @Basic
    @Column(name = "cid")
    public Long getCid() {
        return cid;
    }

    public void setCid(Long cid) {
        this.cid = cid;
    }

    @Basic
    @Column(name = "seller_cids")
    public String getSellerCids() {
        return sellerCids;
    }

    public void setSellerCids(String sellerCids) {
        this.sellerCids = sellerCids;
    }

    @Basic
    @Column(name = "input_pids")
    public String getInputPids() {
        return inputPids;
    }

    public void setInputPids(String inputPids) {
        this.inputPids = inputPids;
    }

    @Basic
    @Column(name = "input_str")
    public String getInputStr() {
        return inputStr;
    }

    public void setInputStr(String inputStr) {
        this.inputStr = inputStr;
    }

    @Basic
    @Column(name = "num")
    public Integer getNum() {
        return num;
    }

    public void setNum(Integer num) {
        this.num = num;
    }

    @Basic
    @Column(name = "valid_thru")
    public Integer getValidThru() {
        return validThru;
    }

    public void setValidThru(Integer validThru) {
        this.validThru = validThru;
    }

    @Basic
    @Column(name = "delist_time")
    public Timestamp getDelistTime() {
        return delistTime;
    }

    public void setDelistTime(Timestamp delistTime) {
        this.delistTime = delistTime;
    }

    @Basic
    @Column(name = "price")
    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    @Basic
    @Column(name = "post_fee")
    public BigDecimal getPostFee() {
        return postFee;
    }

    public void setPostFee(BigDecimal postFee) {
        this.postFee = postFee;
    }

    @Basic
    @Column(name = "express_fee")
    public BigDecimal getExpressFee() {
        return expressFee;
    }

    public void setExpressFee(BigDecimal expressFee) {
        this.expressFee = expressFee;
    }

    @Basic
    @Column(name = "ems_fee")
    public BigDecimal getEmsFee() {
        return emsFee;
    }

    public void setEmsFee(BigDecimal emsFee) {
        this.emsFee = emsFee;
    }

    @Basic
    @Column(name = "freight_payer")
    public String getFreightPayer() {
        return freightPayer;
    }

    public void setFreightPayer(String freightPayer) {
        this.freightPayer = freightPayer;
    }

    @Basic
    @Column(name = "has_invoice")
    public String getHasInvoice() {
        return hasInvoice;
    }

    public void setHasInvoice(String hasInvoice) {
        this.hasInvoice = hasInvoice;
    }

    @Basic
    @Column(name = "increment")
    public String getIncrement() {
        return increment;
    }

    public void setIncrement(String increment) {
        this.increment = increment;
    }

    @Basic
    @Column(name = "approve_status")
    public String getApproveStatus() {
        return approveStatus;
    }

    public void setApproveStatus(String approveStatus) {
        this.approveStatus = approveStatus;
    }

    @Basic
    @Column(name = "postage_id")
    public Long getPostageId() {
        return postageId;
    }

    public void setPostageId(Long postageId) {
        this.postageId = postageId;
    }

    @Basic
    @Column(name = "is_taobao")
    public String getIsTaobao() {
        return isTaobao;
    }

    public void setIsTaobao(String isTaobao) {
        this.isTaobao = isTaobao;
    }

    @Basic
    @Column(name = "is_timing")
    public String getIsTiming() {
        return isTiming;
    }

    public void setIsTiming(String isTiming) {
        this.isTiming = isTiming;
    }

    @Basic
    @Column(name = "second_kill")
    public String getSecondKill() {
        return secondKill;
    }

    public void setSecondKill(String secondKill) {
        this.secondKill = secondKill;
    }

    @Basic
    @Column(name = "violation")
    public String getViolation() {
        return violation;
    }

    public void setViolation(String violation) {
        this.violation = violation;
    }

    @Basic
    @Column(name = "sell_promise")
    public String getSellPromise() {
        return sellPromise;
    }

    public void setSellPromise(String sellPromise) {
        this.sellPromise = sellPromise;
    }

    @Basic
    @Column(name = "shop_name")
    public String getShopName() {
        return shopName;
    }

    public void setShopName(String shopName) {
        this.shopName = shopName;
    }

    @Basic
    @Column(name = "deleted")
    public Boolean getDeleted() {
        return deleted;
    }

    public void setDeleted(Boolean deleted) {
        this.deleted = deleted;
    }

    @Basic
    @Column(name = "inserted_at")
    public Timestamp getInsertedAt() {
        return insertedAt;
    }

    public void setInsertedAt(Timestamp insertedAt) {
        this.insertedAt = insertedAt;
    }

    @Basic
    @Column(name = "updated_at")
    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }

    @Basic
    @Column(name = "has_showcase")
    public Boolean getHasShowcase() {
        return hasShowcase;
    }

    public void setHasShowcase(Boolean hasShowcase) {
        this.hasShowcase = hasShowcase;
    }

    @Basic
    @Column(name = "valid")
    public Boolean getValid() {
        return valid;
    }

    public void setValid(Boolean valid) {
        this.valid = valid;
    }

    @Basic
    @Column(name = "has_discount")
    public Boolean getHasDiscount() {
        return hasDiscount;
    }

    public void setHasDiscount(Boolean hasDiscount) {
        this.hasDiscount = hasDiscount;
    }

    @Basic
    @Column(name = "list_time")
    public Long getListTime() {
        return listTime;
    }

    public void setListTime(Long listTime) {
        this.listTime = listTime;
    }

    @Basic
    @Column(name = "wap_desc")
    public String getWapDesc() {
        return wapDesc;
    }

    public void setWapDesc(String wapDesc) {
        this.wapDesc = wapDesc;
    }

    @Basic
    @Column(name = "wap_detail_url")
    public String getWapDetailUrl() {
        return wapDetailUrl;
    }

    public void setWapDetailUrl(String wapDetailUrl) {
        this.wapDetailUrl = wapDetailUrl;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Item item = (Item) o;

        return this.store.getStoreName().equals(item.getStore().getStoreName());

    }


}
