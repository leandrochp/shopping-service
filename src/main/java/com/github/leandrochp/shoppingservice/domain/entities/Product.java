package com.github.leandrochp.shoppingservice.domain.entities;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class Product {

    private String identifier;
    private Integer amount;

}
