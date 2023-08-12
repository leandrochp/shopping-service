package com.github.leandrochp.shoppingservice.application.web.mapper;

import com.github.leandrochp.shoppingservice.application.web.request.ShopItemRequest;
import com.github.leandrochp.shoppingservice.application.web.request.ShopRequest;
import com.github.leandrochp.shoppingservice.application.web.response.ShopItemResponse;
import com.github.leandrochp.shoppingservice.application.web.response.ShopResponse;
import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import org.springframework.stereotype.Component;

import java.util.stream.Collectors;

@Component
public class ShopMapper {

    public Shop toModel(ShopRequest shopRequest) {
        Shop shop = new Shop();
        shop.setBuyerIdentifier(shopRequest.getBuyerIdentifier());
        if (shopRequest.getItems() != null) {
            shop.setItems(
                    shopRequest.getItems().stream().map(ShopItemRequest::toModel).collect(Collectors.toList())
            );
        }
        return shop;
    }

    public ShopResponse toResponse(Shop shop) {
        ShopResponse response = new ShopResponse();
        response.setBuyerIdentifier(shop.getBuyerIdentifier());
        response.setIdentifier(shop.getIdentifier());
        response.setStatus(shop.getStatus());
        response.setDateShop(shop.getDateShop());
        if (shop.getItems() != null) {
            response.setItems(
                    shop.getItems().stream().map(ShopItemResponse::toResponse).collect(Collectors.toList())
            );
        }
        return response;
    }
}
