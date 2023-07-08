package com.github.leandrochp.shoppingservice.domain.events;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;

public interface ShopEvent {

    void send(Shop shop);
}
