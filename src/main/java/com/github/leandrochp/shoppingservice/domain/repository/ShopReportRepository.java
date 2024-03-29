package com.github.leandrochp.shoppingservice.domain.repository;

import com.github.leandrochp.shoppingservice.domain.shopping.ShopReport;

import java.util.List;

public interface ShopReportRepository {

    List<ShopReport> findAll();
    void incrementShopStatus(String status);
}
