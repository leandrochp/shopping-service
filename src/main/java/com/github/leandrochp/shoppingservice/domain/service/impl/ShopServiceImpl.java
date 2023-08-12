package com.github.leandrochp.shoppingservice.domain.service.impl;

import com.github.leandrochp.shoppingservice.domain.enums.ShopStatus;
import com.github.leandrochp.shoppingservice.domain.message.ShopEvent;
import com.github.leandrochp.shoppingservice.domain.message.ShopMessage;
import com.github.leandrochp.shoppingservice.domain.repository.ProductRepository;
import com.github.leandrochp.shoppingservice.domain.repository.ShopRepository;
import com.github.leandrochp.shoppingservice.domain.service.ShopService;
import com.github.leandrochp.shoppingservice.domain.shopping.Product;
import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.domain.shopping.ShopItem;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopServiceImpl implements ShopService {

    private final ShopRepository shopRepository;
    private final ProductRepository productRepository;
    private final ShopMessage shopMessage;
    private final ShopEvent shopEvent;

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

            log.debug("Notifying of the shop [identifier: {}].", shop.getIdentifier());
            shopMessage.send(shop);

        } catch (Exception ex) {
            log.error("Error saving shop [identifier: {}]. Error: {}", shop.getIdentifier(), ex);
            throw ex;
        }

        return shop;
    }

    @Transactional
    @Override
    public void updateStatus(Shop shop) {
        val status = shop.getStatus();
        try {
            log.debug("Updating status: {} shop [identifier: {}].", status, shop.getIdentifier());

            shop = shopRepository.findByIdentifier(shop.getIdentifier());
            shop.setStatus(status);
            shopRepository.save(shop);

        } catch (Exception ex) {
            log.error("Error updating status shop [identifier: {}]. Error: {}", shop.getIdentifier(), ex);
            throw ex;
        }
    }

    @Override
    public void validate(Shop shop) throws Exception {
        try {
            log.debug("Validating shop [identifier: {}].", shop.getIdentifier());
            shop.setStatus(ShopStatus.SUCCESS.name());

            if (shop.getItems() == null || shop.getItems().isEmpty()) {
                throw new Exception(String.format("Shop identifier: %s without item(s)", shop.getIdentifier()));
            }
            for (ShopItem shopItem : shop.getItems()) {
                Product product = productRepository.findByIdentifier(shopItem.getProductIdentifier());

                if (!isValidShop(shopItem, product)) {
                    shop.setStatus(ShopStatus.ERROR.name());
                    break;
                }
            }
            log.debug("Sending shop [identifier: {}] status {}.", shop.getIdentifier(), shop.getStatus());
            shopEvent.send(shop);

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
