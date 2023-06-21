package com.github.leandrochp.shoppingservice.infrastructure.repositories.entities;

import com.github.leandrochp.shoppingservice.domain.shopping.ShopItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "shop_item")
public class ShopItemEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_identifier")
    private String productIdentifier;

    private Integer amount;
    private Float price;

    @ManyToOne
    @JoinColumn(name = "shop_id")
    private ShopEntity shopEntity;

    public ShopItem toModel() {
        ShopItem shopItem = new ShopItem();
        shopItem.setId(this.id);
        shopItem.setProductIdentifier(this.productIdentifier);
        shopItem.setAmount(this.amount);
        shopItem.setPrice(this.price);

        return shopItem;
    }

}