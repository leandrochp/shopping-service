package com.github.leandrochp.shoppingservice.infrastructure.repository;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.domain.repository.ShopRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repository.entity.ShopEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repository.jpa.ShopJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class H2ShopRepository implements ShopRepository {

    private final ShopJpaRepository shopRepository;

    @Override
    public List<Shop> findAll() {
        return shopRepository.findAll().stream().map(
                ShopEntity::toModel
        ).collect(Collectors.toList());
    }

    @Override
    public Shop findByIdentifier(String identifier) {
        ShopEntity shopEntity = shopRepository.findByIdentifier(identifier);
        if (shopEntity != null) {
            return shopEntity.toModel();
        }
        return null;
    }

    @Override
    public void save(Shop shop) {
        shopRepository.save(ShopEntity.toEntity(shop));
    }

}
