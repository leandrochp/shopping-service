package com.github.leandrochp.shoppingservice.application.web.controller;

import com.github.leandrochp.shoppingservice.application.web.mapper.ShopReportMapper;
import com.github.leandrochp.shoppingservice.application.web.response.ShopReportResponse;
import com.github.leandrochp.shoppingservice.domain.service.ShopReportService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop-report")
public class ShopReportController {

    private final ShopReportService shopReportService;
    private final ShopReportMapper mapper;

    @GetMapping
    public List<ShopReportResponse> getReports() {
        return shopReportService.findAll().stream().map(
                mapper::toResponse
        ).collect(Collectors.toList());
    }
}
