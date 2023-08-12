package com.github.leandrochp.shoppingservice.infrastructure.repository.entity;

import com.github.leandrochp.shoppingservice.domain.shopping.Shop;
import com.github.leandrochp.shoppingservice.domain.shopping.ShopItem;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
@Entity(name = "shop")
public class ShopEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "buyer_identifier")
    private String buyerIdentifier;

    private String identifier;
    private String status;

    @Column(name = "date_shop")
    private LocalDate dateShop;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shopEntity")
    private List<ShopItemEntity> items;

    public Shop toModel() {
        Shop shop = new Shop();
        shop.setId(this.id);
        shop.setBuyerIdentifier(this.buyerIdentifier);
        shop.setIdentifier(this.identifier);
        shop.setStatus(this.status);
        shop.setDateShop(this.dateShop);
        shop.setItems(
                this.items.stream().map(ShopItemEntity::toModel).collect(Collectors.toList())
        );

        return shop;
    }

    public static ShopEntity toEntity(Shop shop) {
        ShopEntity shopEntity = new ShopEntity();

        shopEntity.setId(shop.getId());
        shopEntity.setBuyerIdentifier(shop.getBuyerIdentifier());
        shopEntity.setIdentifier(shop.getIdentifier());
        shopEntity.setStatus(shop.getStatus());
        shopEntity.setDateShop(shop.getDateShop());
        shopEntity.setShopItemEntities(shop.getItems());

        return shopEntity;
    }

    private void setShopItemEntities(List<ShopItem> shopItems) {
        if (shopItems != null) {
            List<ShopItemEntity> shopItemEntities = new ArrayList<>();

            for (ShopItem shopItem : shopItems) {
                ShopItemEntity shopItemEntity = new ShopItemEntity();
                shopItemEntity.setId(shopItem.getId());
                shopItemEntity.setProductIdentifier(shopItem.getProductIdentifier());
                shopItemEntity.setAmount(shopItem.getAmount());
                shopItemEntity.setPrice(shopItem.getPrice());
                shopItemEntity.setShopEntity(this);

                shopItemEntities.add(shopItemEntity);
            }
            this.items = shopItemEntities;
        }
    }
}
