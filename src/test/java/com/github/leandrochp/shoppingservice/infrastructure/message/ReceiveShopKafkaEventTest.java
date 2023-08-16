package com.github.leandrochp.shoppingservice.infrastructure.message;

import com.github.leandrochp.shoppingservice.domain.service.ShopReportService;
import com.github.leandrochp.shoppingservice.domain.service.ShopService;
import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
public class ReceiveShopKafkaEventTest {

    @InjectMocks
    private ReceiveShopKafkaEvent receiveShopKafkaEvent;

    @Mock
    private ShopService shopService;
    @Mock
    private ShopReportService shopReportService;

    private final Shop shop = new Shop();

    @Test
    public void testSuccessfulEventReceived() {

        receiveShopKafkaEvent.listenShopEvent(shop);

        Mockito.verify(
                shopService,
                Mockito.times(1)
        ).updateStatus(shop);
    }

    @Test
    public void testSuccessfulEventReportReceived() {

        receiveShopKafkaEvent.listenShopEventReport(shop);

        Mockito.verify(
                shopReportService,
                Mockito.times(1)
        ).incrementShopStatus(shop);
    }
}
