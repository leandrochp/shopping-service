package com.github.leandrochp.shoppingservice.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopItem {

    private String productIdentifier;
    private Integer amount;
    private Float price;

}
