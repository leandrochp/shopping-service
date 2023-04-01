package com.github.leandrochp.shoppingservice.infrastructure.repositories.entities;

import com.github.leandrochp.shoppingservice.domain.entities.Shop;
import com.github.leandrochp.shoppingservice.domain.entities.ShopItem;
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

    private String identifier;
    private String status;

    @Column(name = "date_shop")
    private LocalDate dateShop;

    @OneToMany(fetch = FetchType.EAGER, cascade = CascadeType.ALL, mappedBy = "shopEntity")
    private List<ShopItemEntity> items;

    public Shop toModel() {
        Shop shop = new Shop();
        shop.setIdentifier(this.identifier);
        shop.setStatus(this.status);
        shop.setDateShop(this.dateShop);
        shop.setItems(
                this.getItems().stream().map(ShopItemEntity::toModel).collect(Collectors.toList())
        );

        return shop;
    }

    public static ShopEntity toEntity(Shop shop) {
        ShopEntity shopEntity = new ShopEntity();

        shopEntity.setIdentifier(shop.getIdentifier());
        shopEntity.setStatus(shop.getStatus());
        shopEntity.setDateShop(shop.getDateShop());

        shopEntity.setItems(getShopItemEntities(shop.getItems()));
        for (ShopItemEntity shopItemEntity : shopEntity.getItems()) {
            shopItemEntity.setShopEntity(shopEntity);
        }
        return shopEntity;
    }

    private static List<ShopItemEntity> getShopItemEntities(List<ShopItem> shopItems) {
        List<ShopItemEntity> items = new ArrayList<>();
        for (ShopItem shopItem : shopItems) {
            ShopItemEntity shopItemEntity = new ShopItemEntity();
            shopItemEntity.setProductIdentifier(shopItem.getProductIdentifier());
            shopItemEntity.setAmount(shopItem.getAmount());
            shopItemEntity.setPrice(shopItem.getPrice());

            items.add(shopItemEntity);
        }
        return items;
    }
}
