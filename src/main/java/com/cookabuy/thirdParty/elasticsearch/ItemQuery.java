package com.cookabuy.thirdParty.elasticsearch;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author yejinbiao
 * @create 2016-12-19-上午11:26
 */
@Setter
@Getter
@ToString
public class ItemQuery {
    private String title;

    private String store;

    private String location;

    private Integer priceLow = 0;

    private Integer priceHight = Integer.MAX_VALUE;

    private Integer from=0;

    private Integer size=10;
}
