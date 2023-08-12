package com.github.leandrochp.shoppingservice.domain.repository;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;

import java.util.List;

public interface ShopRepository {

    List<Shop> findAll();
    Shop findByIdentifier(String identifier);
    void save(Shop shop);
}
