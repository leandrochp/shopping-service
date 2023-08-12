package com.github.leandrochp.shoppingservice.domain.service;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.domain.shopping.ShopReport;

import java.util.List;

public interface ShopReportService {

    List<ShopReport> findAll();
    void incrementShopStatus(Shop shop);
}
