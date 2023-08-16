package com.github.leandrochp.shoppingservice.infrastructure.message;

import com.github.leandrochp.shoppingservice.domain.service.ShopService;
import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;

@ExtendWith(SpringExtension.class)
public class ReceiveShopKafkaMessageTest {

    @InjectMocks
    private ReceiveShopKafkaMessage receiveShopKafkaMessage;

    @Mock
    private ShopService shopService;

    private final Shop shop = new Shop();

    @Test
    public void testSuccessfulMessageReceived() throws Exception {

        receiveShopKafkaMessage.listenShopTopic(
                shop,
                "key",
                "partitionId",
                String.valueOf(
                        ZonedDateTime.of(
                                LocalDateTime.now(),
                                ZoneId.systemDefault()
                        ).toEpochSecond()
                )
        );

        Mockito.verify(
                shopService,
                Mockito.times(1)
        ).validate(shop);
    }
}
