package com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas;

import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ShopJpaRepository extends JpaRepository<ShopEntity, Long> {
}
