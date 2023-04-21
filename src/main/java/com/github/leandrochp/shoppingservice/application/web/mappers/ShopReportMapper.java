package com.github.leandrochp.shoppingservice.application.web.mappers;

import com.github.leandrochp.shoppingservice.application.web.responses.ShopReportResponse;
import com.github.leandrochp.shoppingservice.domain.entities.ShopReport;
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
