package com.cookabuy.constant;

/**
 * 广告，推荐商品，推荐商家等运营元素可以分布给多个页面的同时还可以分布在一个页面的不同区域，
 * location就表示区域，该类包含以上元素所在页面位置的名字常数, i.g  girl 表示女装区 boy表示男装区
 * @see PageContant
 * @author yejinbiao
 * @create 2016-12-30-13:18
 */

public class LocationConstant {
    public static final String LEFT_BLOCK = "left";

    public static final String TOP_BLOCK = "top";

    public static final String RIGHT_BLOCK = "right";

    public static final String BOTTOM_BLOCK = "bottom";

    public static final String BOY_BLOCK = "boy";

    public static final String GIRL_BLOCK = "girl";

    public static final String [] LOCATION_ARRAY = new String[] {LEFT_BLOCK, TOP_BLOCK, RIGHT_BLOCK, BOTTOM_BLOCK, BOY_BLOCK, GIRL_BLOCK};

}
