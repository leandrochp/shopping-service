package com.github.leandrochp.shoppingservice.domain.services;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.entities.ShopReport;

import java.util.List;

public interface ShopReportService {

    List<ShopReport> findAll();
    void incrementShopStatus(Shop shop);
}
