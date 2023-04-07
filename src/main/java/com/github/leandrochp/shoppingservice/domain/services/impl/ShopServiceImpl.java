package com.github.leandrochp.shoppingservice.domain.services.impl;

import com.github.leandrochp.shoppingservice.domain.entities.Product;
import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.entities.ShopItem;
import com.github.leandrochp.shoppingservice.domain.enums.ShopStatus;
import com.github.leandrochp.shoppingservice.domain.events.TopicEventMessage;
import com.github.leandrochp.shoppingservice.domain.events.TopicMessage;
import com.github.leandrochp.shoppingservice.domain.repositories.ProductRepository;
import com.github.leandrochp.shoppingservice.domain.repositories.ShopRepository;
import com.github.leandrochp.shoppingservice.domain.services.ShopService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;
    private final TopicMessage sendTopicMessage;
    private final TopicEventMessage sendTopicEventMessage;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Override
    public Shop save(Shop shop) {
        log.debug("Generating shop identifier.");
        shop.setIdentifier(UUID.randomUUID().toString());
        shop.setDateShop(LocalDate.now());

        shop.setStatus(ShopStatus.PENDING.name());

        log.debug("Saving shop [identifier: {}] in the database.", shop.getIdentifier());
        shopRepository.save(shop);

        log.debug("Notifying listener of the shop [identifier: {}].", shop.getIdentifier());
        sendTopicMessage.sendTopicMessage(shop);

        return shop;
    }

    @Override
    public void updateStatus(Shop shop) {
        final String status = shop.getStatus();

        try {
            log.debug("Status of the shop received in topic: [identifier: {}].", shop.getIdentifier());

            shop = shopRepository.findByIdentifier(shop.getIdentifier());
            shop.setStatus(status);
            shopRepository.save(shop);

        } catch (Exception ex) {
            log.error("Error shop processing [identifier: {}]. Error: {}", shop.getIdentifier(), ex.getMessage());
        }
    }

    @Override
    public void validate(Shop shop) {
        try {
            boolean success;
            log.debug("Shop received in topic: [identifier: {}].", shop.getIdentifier());

            success = true;
            for (ShopItem shopItem : shop.getItems()) {
                Product product = productRepository.findByIdentifier(shopItem.getProductIdentifier());

                if (!isValidShop(shopItem, product)) {
                    success = false;
                    break;
                }
            }

            if (success) sendSuccess(shop);
            else {
                sendError(shop);
            }

        } catch (Exception ex) {
            log.error("Error shop processing [identifier: {}]. Error: {}", shop.getIdentifier(), ex.getMessage());
        }
    }

    private boolean isValidShop(ShopItem shopItem, Product product) {
        return product != null && product.getAmount() >= shopItem.getAmount();
    }

    private void sendSuccess(Shop shop) {
        log.debug("Shop [identifier: {}] successful.", shop.getIdentifier());
        shop.setStatus(ShopStatus.SUCCESS.name());

        sendTopicEventMessage.sendTopicEventMessage(shop);
    }

    private void sendError(Shop shop) {
        log.debug("Error shop processing [identifier: {}].", shop.getIdentifier());
        shop.setStatus(ShopStatus.ERROR.name());

        sendTopicEventMessage.sendTopicEventMessage(shop);
    }
}
