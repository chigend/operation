package com.cookabuy.thirdParty.elasticsearch;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yejinbiao
 * @create 2016-12-19-上午11:26
 */
@Setter
@Getter
public class ItemQuery {
    private String title;

    private String store;

    private Integer priceLow = 0;

    private Integer priceHight = Integer.MAX_VALUE;
}
