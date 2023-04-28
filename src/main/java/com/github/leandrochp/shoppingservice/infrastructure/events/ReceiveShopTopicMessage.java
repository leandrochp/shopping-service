package com.github.leandrochp.shoppingservice.infrastructure.events;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.stereotype.Component;

import java.sql.Timestamp;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiveShopTopicMessage {

    private static final String SHOP_TOPIC_NAME = "SHOP_TOPIC";

    private final ShopService shopService;

    @KafkaListener(topics = SHOP_TOPIC_NAME, groupId = "group")
    public void listenShopTopic(
            Shop shop,
            @Header(KafkaHeaders.RECEIVED_MESSAGE_KEY) String key,
            @Header(KafkaHeaders.RECEIVED_PARTITION_ID) String partitionId,
            @Header(KafkaHeaders.RECEIVED_TIMESTAMP) String timestamp) throws Exception {
        log.debug(
                "Shop received in topic: [identifier: {}, key: {}, partition: {}, date: {}].",
                shop.getIdentifier(),
                key,
                partitionId,
                new Timestamp(Long.parseLong(timestamp))
        );

        shopService.validate(shop);
    }

}
