package com.github.leandrochp.shoppingservice.infrastructure.repositories;

import com.github.leandrochp.shoppingservice.domain.shopping.Product;
import com.github.leandrochp.shoppingservice.domain.repositories.ProductRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ProductEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas.ProductJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@RequiredArgsConstructor
@Repository
public class H2ProductRepository implements ProductRepository {

    private final ProductJpaRepository productRepository;

    @Override
    public Product findByIdentifier(String identifier) {
        ProductEntity product = productRepository.findByIdentifier(identifier);
        if (product != null) {
            return product.toModel();
        }
        return null;
    }
}
