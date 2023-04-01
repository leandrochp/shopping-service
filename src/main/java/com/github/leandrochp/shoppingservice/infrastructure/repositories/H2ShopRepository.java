package com.github.leandrochp.shoppingservice.infrastructure.repositories;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.repositories.ShopRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ShopEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas.ShopJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class H2ShopRepository implements ShopRepository {

    @Autowired
    private ShopJpaRepository shopRepository;

    public List<Shop> findAll() {
        return shopRepository.findAll().stream().map(
                ShopEntity::toModel
        ).collect(Collectors.toList());
    }

    public Shop save(Shop shop) {
        return shopRepository.save(ShopEntity.toEntity(shop)).toModel();
    }

}
