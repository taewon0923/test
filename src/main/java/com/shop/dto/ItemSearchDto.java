package com.shop.dto;

import lombok.Getter;
import lombok.Setter;

@Getter @Setter
public class ItemSearchDto {

    private String searchDateType;

    private String searchSellStatus;

    private String searchBy;

    private String searchQuery;

}
