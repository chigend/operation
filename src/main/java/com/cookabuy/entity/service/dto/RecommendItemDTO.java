package com.cookabuy.entity.service.dto;

import com.fasterxml.jackson.annotation.JsonFormat;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author yejinbiao
 * @create 2016-12-29-13:26
 */
@Setter
@Getter
@ToString
public class RecommendItemDTO {
    private Integer id;

    private Long itemId;

    private String picUrl;

    private String title;

    private BigDecimal price;

    private String shopName;

    private String market;


    @JsonFormat(pattern = "yyyy-MM-dd ",timezone = "GMT+8")
    private Date insertedAt;
}
