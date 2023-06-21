package com.github.leandrochp.shoppingservice.domain.services;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;

import java.util.List;

public interface ShopService {

    List<Shop> findAll();
    Shop save(Shop shop);
    void validate(Shop shop) throws Exception;
    void updateStatus(Shop shop);
}
