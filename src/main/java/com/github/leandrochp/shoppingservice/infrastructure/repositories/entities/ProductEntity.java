package com.github.leandrochp.shoppingservice.infrastructure.repositories.entities;

import com.github.leandrochp.shoppingservice.domain.entities.Product;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@NoArgsConstructor
@Entity(name = "product")
public class ProductEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "product_identifier")
    private String identifier;

    private Integer amount;

    public Product toModel() {
        Product product = new Product();
        product.setIdentifier(this.identifier);
        product.setAmount(this.amount);

        return product;
    }
}
