package com.github.leandrochp.shoppingservice.infrastructure.repository;

import com.github.leandrochp.shoppingservice.domain.shopping.ShopReport;
import com.github.leandrochp.shoppingservice.domain.repository.ShopReportRepository;
import com.github.leandrochp.shoppingservice.infrastructure.repository.entity.ShopReportEntity;
import com.github.leandrochp.shoppingservice.infrastructure.repository.jpa.ShopReportJpaRepository;
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
