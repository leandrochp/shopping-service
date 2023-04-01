package com.github.leandrochp.shoppingservice.application.web.requests;

import lombok.Data;

import java.util.List;

@Data
public class ShopRequest {

    private List<ShopItemRequest> items;
}