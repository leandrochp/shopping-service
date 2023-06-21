package com.github.leandrochp.shoppingservice.domain.shopping;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private String identifier;
    private Integer amount;
}
