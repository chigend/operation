package com.cookabuy.entity.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;
import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2016-12-29-13:26
 */
@Setter
@Getter
@ToString
public class RecommendItemDTO {
    private UUID id;

    private UUID itemId;

    private String picUrl;

    private String title;

    private BigDecimal price;

    private String shopName;

    private String market;

    private String stall;

    private Integer weight = 0;


    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date createTime;
}
