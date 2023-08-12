package com.github.leandrochp.shoppingservice.application.web.mapper;

import com.github.leandrochp.shoppingservice.application.web.response.ShopReportResponse;
import com.github.leandrochp.shoppingservice.domain.shopping.ShopReport;
import org.springframework.stereotype.Component;

@Component
public class ShopReportMapper {

    public ShopReportResponse toResponse(ShopReport shopReport) {
        ShopReportResponse response = new ShopReportResponse();
        response.setStatus(shopReport.getStatus());
        response.setAmount(shopReport.getAmount());

        return response;
    }
}
