package com.github.leandrochp.shoppingservice.domain.events;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;

public interface TopicMessage {

    void sendTopic(Shop shop);
}
