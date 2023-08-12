package com.github.leandrochp.shoppingservice.domain.message;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;

public interface ShopEvent {

    void send(Shop shop);
}
