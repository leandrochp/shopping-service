package com.github.leandrochp.shoppingservice.domain.events;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;

public interface TopicEventMessage {

    void sendTopicEvent(Shop shop);
}
