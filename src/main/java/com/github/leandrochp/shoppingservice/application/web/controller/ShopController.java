package com.github.leandrochp.shoppingservice.application.web.controller;

import com.github.leandrochp.shoppingservice.application.web.mapper.ShopMapper;
import com.github.leandrochp.shoppingservice.application.web.request.ShopRequest;
import com.github.leandrochp.shoppingservice.application.web.response.ShopResponse;
import com.github.leandrochp.shoppingservice.domain.service.ShopService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@RestController
@RequestMapping("/shop")
public class ShopController {

    private final ShopService shopService;
    private final ShopMapper mapper;

    @GetMapping
    public List<ShopResponse> getShops() {
        return shopService.findAll().stream().map(
                mapper::toResponse
        ).collect(Collectors.toList());
    }

    @PostMapping
    public ShopResponse saveShop(@RequestBody ShopRequest shopRequest) {
        return mapper.toResponse(
                shopService.save(mapper.toModel(shopRequest))
        );
    }

}
