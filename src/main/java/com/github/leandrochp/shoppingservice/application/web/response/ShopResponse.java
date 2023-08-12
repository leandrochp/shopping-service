package com.github.leandrochp.shoppingservice.application.web.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.time.LocalDate;
import java.util.List;

@Data
public class ShopResponse {

    @JsonProperty("buyer_identifier")
    private String buyerIdentifier;
    private String identifier;
    private String status;
    @JsonProperty("date_shop")
    private LocalDate dateShop;
    private List<ShopItemResponse> items;
}
