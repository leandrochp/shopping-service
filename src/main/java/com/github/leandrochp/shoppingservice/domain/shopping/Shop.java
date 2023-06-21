package com.github.leandrochp.shoppingservice.domain.shopping;

import com.github.leandrochp.shoppingservice.domain.enums.ShopStatus;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.util.List;

@Data
@NoArgsConstructor
public class Shop {

    private Long id;
    private String buyerIdentifier;
    private String identifier;
    private String status = ShopStatus.PENDING.name();
    private LocalDate dateShop;
    private List<ShopItem> items;
}
