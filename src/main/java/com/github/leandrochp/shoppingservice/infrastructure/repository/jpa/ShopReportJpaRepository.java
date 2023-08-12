package com.github.leandrochp.shoppingservice.infrastructure.repository.jpa;

import com.github.leandrochp.shoppingservice.infrastructure.repository.entity.ShopReportEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface ShopReportJpaRepository extends JpaRepository<ShopReportEntity, Long> {

    @Modifying
    @Query(
            value = "update shop_report sr set sr.amount = sr.amount + 1 where sr.status = :status",
            nativeQuery = true
    )
    void incrementShopStatus(@Param("status") String status);
}
