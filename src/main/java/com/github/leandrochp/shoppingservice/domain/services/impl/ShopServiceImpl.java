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

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;
    private final TopicMessage topicMessage;
    private final TopicEventMessage topicEventMessage;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll();
    }

    @Transactional
    @Override
    public Shop save(Shop shop) {
        try {
            shop.setIdentifier(UUID.randomUUID().toString());
            log.debug("Saving shop [identifier: {}].", shop.getIdentifier());
            shop.setDateShop(LocalDate.now());

            log.debug("Saving shop [identifier: {}] in the database.", shop.getIdentifier());
            shopRepository.save(shop);

            log.debug("Notifying listener of the shop [identifier: {}].", shop.getIdentifier());
            topicMessage.sendTopic(shop);

        } catch (Exception ex) {
            log.error("Error saving shop [identifier: {}]. Error: {}", shop.getIdentifier(),
                    ex.getMessage());
            throw ex;
        }

        return shop;
    }

    @Transactional
    @Override
    public void updateStatus(Shop shop) {
        final String status = shop.getStatus();
        try {
            log.debug("Updating status: {} shop [identifier: {}].", status, shop.getIdentifier());

            shop = shopRepository.findByIdentifier(shop.getIdentifier());
            shop.setStatus(status);
            shopRepository.save(shop);

        } catch (Exception ex) {
            log.error("Error updating status shop [identifier: {}]. Error: {}", shop.getIdentifier(),
                    ex.getMessage());
            throw ex;
        }
    }

    @Override
    public void validate(Shop shop) {
        try {
            log.debug("Validating shop [identifier: {}].", shop.getIdentifier());

            shop.setStatus(ShopStatus.SUCCESS.name());
            for (ShopItem shopItem : shop.getItems()) {
                Product product = productRepository.findByIdentifier(shopItem.getProductIdentifier());

                if (!isValidShop(shopItem, product)) {
                    shop.setStatus(ShopStatus.ERROR.name());
                    break;
                }
            }
            log.debug("Send shop [identifier: {}] status {}.", shop.getIdentifier(), shop.getStatus());
            topicEventMessage.sendTopicEvent(shop);

        } catch (Exception ex) {
            log.error("Error validating shop [identifier: {}]. Error: {}", shop.getIdentifier(),
                    ex.getMessage());
            throw ex;
        }
    }

    private boolean isValidShop(ShopItem shopItem, Product product) {
        return product != null && product.getAmount() >= shopItem.getAmount();
    }

}
