package com.github.leandrochp.shoppingservice.infrastructure.message;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.infrastructure.message.enums.Topic;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class SendShopKafkaEventTest {

    @InjectMocks
    private SendShopKafkaEvent sendShopKafkaEvent;

    @Mock
    private KafkaTemplate<String, Shop> kafkaTemplate;

    private final Shop shop = new Shop();

    @Test
    public void testSendEvent() {

        sendShopKafkaEvent.send(shop);

        Mockito.verify(
                kafkaTemplate,
                Mockito.times(1)
        ).send(Topic.SHOP_TOPIC_EVENT.name(), shop);
    }
}
