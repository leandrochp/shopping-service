package com.github.leandrochp.shoppingservice.infrastructure.events;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.events.TopicEventMessage;
import com.github.leandrochp.shoppingservice.domain.events.TopicMessage;
import com.github.leandrochp.shoppingservice.infrastructure.events.enums.TopicName;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendShopTopicMessage implements TopicMessage, TopicEventMessage {

    private final KafkaTemplate<String, Shop> kafkaTemplate;

    @Override
    public void sendTopic(Shop shop) {
        kafkaTemplate.send(TopicName.SHOP_TOPIC.name(), shop.getBuyerIdentifier(), shop);
    }

    @Override
    public void sendTopicEvent(Shop shop) {
        kafkaTemplate.send(TopicName.SHOP_TOPIC_EVENT.name(), shop);
    }

}
