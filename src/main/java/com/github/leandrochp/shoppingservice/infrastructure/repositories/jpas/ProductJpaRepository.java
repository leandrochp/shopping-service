package com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas;

import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ProductEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ProductJpaRepository extends JpaRepository<ProductEntity, Long> {

    @Query("From product p where p.identifier = :identifier")
    ProductEntity findByIdentifier(@Param("identifier") String identifier);
}
