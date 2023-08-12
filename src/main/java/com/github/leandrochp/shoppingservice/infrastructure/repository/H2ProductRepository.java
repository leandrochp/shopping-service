package com.github.leandrochp.shoppingservice.infrastructure.repository;

import com.github.leandrochp.shoppingservice.domain.shopping.Product;
import com.github.leandrochp.shoppingservice.domain.repository.ProductRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repository.entity.ProductEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repository.jpa.ProductJpaRepository;
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
