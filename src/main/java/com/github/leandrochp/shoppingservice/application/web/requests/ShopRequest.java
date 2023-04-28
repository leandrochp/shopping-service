package com.github.leandrochp.shoppingservice.application.web.requests;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;

@Data
public class ShopRequest {

    @JsonProperty("buyer_identifier")
    private String buyerIdentifier;
    private List<ShopItemRequest> items;
}