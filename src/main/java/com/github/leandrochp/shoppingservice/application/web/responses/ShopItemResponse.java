package com.github.leandrochp.shoppingservice.application.web.responses;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.github.leandrochp.shoppingservice.domain.entities.ShopItem;
import lombok.Data;

@Data
public class ShopItemResponse {

    @JsonProperty("product_identifier")
    private String productIdentifier;
    private Integer amount;
    private Float price;

    public static ShopItemResponse toResponse(ShopItem shopItem) {
        ShopItemResponse response = new ShopItemResponse();
        response.setProductIdentifier(shopItem.getProductIdentifier());
        response.setAmount(shopItem.getAmount());
        response.setPrice(shopItem.getPrice());

        return response;
    }
}