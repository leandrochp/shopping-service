package com.github.leandrochp.shoppingservice.infrastructure.repositories;

import com.github.leandrochp.shoppingservice.domain.shopping.ShopReport;
import com.github.leandrochp.shoppingservice.domain.repositories.ShopReportRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.entities.ShopReportEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repositories.jpas.ShopReportJpaRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Repository
public class H2ShopReportRepository implements ShopReportRepository {

    private final ShopReportJpaRepository shopReportJpaRepository;

    @Override
    public List<ShopReport> findAll() {
        return shopReportJpaRepository.findAll().stream().map(
                ShopReportEntity::toModel
        ).collect(Collectors.toList());
    }

    @Override
    public void incrementShopStatus(String status) {
        shopReportJpaRepository.incrementShopStatus(status);
    }

}
