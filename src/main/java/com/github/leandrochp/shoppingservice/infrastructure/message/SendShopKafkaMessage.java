package com.github.leandrochp.shoppingservice.infrastructure.message;

import com.github.leandrochp.shoppingservice.domain.message.ShopMessage;
import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.infrastructure.message.enums.Topic;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class SendShopKafkaMessage implements ShopMessage {

    private final KafkaTemplate<String, Shop> kafkaTemplate;

    @Override
    public void send(Shop shop) {
        kafkaTemplate.send(Topic.SHOP_TOPIC.name(), shop.getBuyerIdentifier(), shop);
    }

}
