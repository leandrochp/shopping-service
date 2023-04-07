package com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas;

import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ShopEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopJpaRepository extends JpaRepository<ShopEntity, Long> {

    @Query("From shop s where s.identifier = :identifier")
    ShopEntity findByIdentifier(@Param("identifier") String identifier);
}
