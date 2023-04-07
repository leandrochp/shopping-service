package com.github.leandrochp.shoppingservice.domain.repositories;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;

import java.util.List;

public interface ShopRepository {

    List<Shop> findAll();
    Shop findByIdentifier(String identifier);
    void save(Shop shop);
}
