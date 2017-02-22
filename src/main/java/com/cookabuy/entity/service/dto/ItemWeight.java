package com.cookabuy.entity.service.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.UUID;

/**
 * @author yejinbiao
 * @create 2017-02-22-上午10:31
 */

@Setter
@Getter
public class ItemWeight{
    public ItemWeight() {
    }

    private UUID id;
    private Integer weight;
}