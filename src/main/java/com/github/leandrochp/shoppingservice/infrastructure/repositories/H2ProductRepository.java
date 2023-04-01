package com.github.leandrochp.shoppingservice.infrastructure.repositories;

import com.github.leandrochp.shoppingservice.domain.entities.Product;
import com.github.leandrochp.shoppingservice.domain.repositories.ProductRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ProductEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas.ProductJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public class H2ProductRepository implements ProductRepository {

    @Autowired
    private ProductJpaRepository productRepository;

    @Override
    public Product findByIdentifier(String identifier) {
        ProductEntity product = productRepository.findByIdentifier(identifier);
        if (product != null) {
            return product.toModel();
        }
        return null;
    }
}
