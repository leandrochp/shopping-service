package com.github.leandrochp.shoppingservice.domain.events;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;

public interface TopicMessage {

    void sendTopic(Shop shop);
}
