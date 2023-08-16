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
public class SendShopKafkaMessageTest {

    @InjectMocks
    private SendShopKafkaMessage sendShopKafkaMessage;

    @Mock
    private KafkaTemplate<String, Shop> kafkaTemplate;

    @Test
    public void testSendMessage() {
        Shop shop = new Shop();
        shop.setStatus("SUCCESS");
        shop.setBuyerIdentifier("buyer-test");

        sendShopKafkaMessage.send(shop);

        Mockito.verify(
                kafkaTemplate,
                Mockito.times(1)
        ).send(Topic.SHOP_TOPIC.name(), shop.getBuyerIdentifier(), shop);
    }
}
