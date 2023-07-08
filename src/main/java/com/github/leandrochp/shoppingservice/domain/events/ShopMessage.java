package com.github.leandrochp.shoppingservice.domain.events;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;

public interface ShopMessage {

    void send(Shop shop);
}
