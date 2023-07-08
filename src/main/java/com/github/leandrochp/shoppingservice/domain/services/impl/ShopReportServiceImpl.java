package com.github.leandrochp.shoppingservice.domain.services.impl;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.domain.shopping.ShopReport;
import com.github.leandrochp.shoppingservice.domain.repositories.ShopReportRepository;
import com.github.leandrochp.shoppingservice.domain.services.ShopReportService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ShopReportServiceImpl implements ShopReportService {

    private final ShopReportRepository shopReportRepository;

    @Override
    public List<ShopReport> findAll() {
        return shopReportRepository.findAll();
    }

    @Transactional
    @Override
    public void incrementShopStatus(Shop shop) {
        try {
            log.debug("Increment shop status [identifier: {}].", shop.getIdentifier());
            shopReportRepository.incrementShopStatus(shop.getStatus());

        } catch (Exception ex) {
            log.error("Error to increment shop status [identifier: {}]. Error: {}", shop.getIdentifier(),
                    ex.getMessage()
            );
        }
    }
}
