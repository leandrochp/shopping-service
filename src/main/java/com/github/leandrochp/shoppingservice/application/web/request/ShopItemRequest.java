package com.github.leandrochp.shoppingservice.application.web.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.leandrochp.shoppingservice.domain.shopping.ShopItem;
import lombok.Data;

@Data
public class ShopItemRequest {

    @JsonProperty("product_identifier")
    private String productIdentifier;
    private Integer amount;
    private Float price;

    public static ShopItem toModel(ShopItemRequest shopItemRequest) {
        ShopItem shopItem = new ShopItem();
        shopItem.setProductIdentifier(shopItemRequest.getProductIdentifier());
        shopItem.setAmount(shopItemRequest.getAmount());
        shopItem.setPrice(shopItemRequest.getPrice());

        return shopItem;
    }
}
