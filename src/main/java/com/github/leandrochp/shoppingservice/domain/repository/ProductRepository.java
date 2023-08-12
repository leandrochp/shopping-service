package com.github.leandrochp.shoppingservice.domain.repository;

import com.github.leandrochp.shoppingservice.domain.shopping.Product;

public interface ProductRepository {

    Product findByIdentifier(String identifier);
}
