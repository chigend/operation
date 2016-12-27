package com.cookabuy.thirdParty.elasticsearch;

import lombok.Getter;
import lombok.Setter;

/**
 * @author yejinbiao
 * @create 2016-12-27-16:04
 */
@Setter
@Getter
public class StoreQuery {
    private String storeName;

    private String location;

    private Integer from=0;

    private Integer size=10;

}
