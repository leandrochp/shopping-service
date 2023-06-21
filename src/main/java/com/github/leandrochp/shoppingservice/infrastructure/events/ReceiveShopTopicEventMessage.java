package com.github.leandrochp.shoppingservice.infrastructure.events;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.domain.services.ShopReportService;
import com.github.leandrochp.shoppingservice.domain.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class ReceiveShopTopicEventMessage {

    private static final String SHOP_TOPIC_EVENT_NAME = "SHOP_TOPIC_EVENT";

    private final ShopService shopService;
    private final ShopReportService shopReportService;

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group")
    public void listenShopTopicEvent(Shop shop) {
        log.debug("Shop received in topic: [identifier: {}].", shop.getIdentifier());
        shopService.updateStatus(shop);
    }

    @KafkaListener(topics = SHOP_TOPIC_EVENT_NAME, groupId = "group_report")
    public void listenShopTopicEventToShopReport(Shop shop) {
        log.debug("Shop received to increment shop status in topic: [identifier: {}].", shop.getIdentifier());
        shopReportService.incrementShopStatus(shop);
    }

}
