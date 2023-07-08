package com.github.leandrochp.shoppingservice.infrastructure.events;

import com.github.leandrochp.shoppingservice.domain.events.ShopMessage;
import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.infrastructure.events.enums.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendShopMessage implements ShopMessage {

    private final KafkaTemplate<String, Shop> kafkaTemplate;

    @Override
    public void send(Shop shop) {
        kafkaTemplate.send(Topic.SHOP_TOPIC.name(), shop.getBuyerIdentifier(), shop);
    }

}
