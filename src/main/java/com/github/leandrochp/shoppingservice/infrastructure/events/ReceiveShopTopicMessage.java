package com.github.leandrochp.shoppingservice.infrastructure.events;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.services.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor
@Component
public class ReceiveShopTopicMessage {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC" ;

    private final ShopService shopService;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group_id")
    public void listenShopTopic(Shop shop) {
        shopService.validate(shop);
    }

}
