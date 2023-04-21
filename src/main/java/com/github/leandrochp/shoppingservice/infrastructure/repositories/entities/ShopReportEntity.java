package com.github.leandrochp.shoppingservice.infrastructure.repositories.entities;

import com.github.leandrochp.shoppingservice.domain.entities.ShopReport;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Data
@NoArgsConstructor
@Entity(name = "shop_report")
public class ShopReportEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String status;
    private Integer amount;

    public ShopReport toModel() {
        ShopReport shopReport = new ShopReport();
        shopReport.setStatus(this.status);
        shopReport.setAmount(this.amount);

        return shopReport;
    }
}