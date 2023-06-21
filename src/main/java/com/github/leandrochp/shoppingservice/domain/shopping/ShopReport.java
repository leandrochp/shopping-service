package com.github.leandrochp.shoppingservice.domain.shopping;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShopReport {

    private String status;
    private Integer amount;
}
