package com.github.leandrochp.shoppingservice.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Shop {

    private Long id;
    private String identifier;
    private String status;
    private LocalDate dateShop;
    private List<ShopItem> items;
}
