package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.WarehouseStock;

public record WarehouseStockDto(
        Integer amount,

        Double pct,

        Double price,

        //@JsonProperty("stockvalue")
        Double stockValue,

        Integer period,

        Integer articleId
) {

    public WarehouseStock toWarehouseStock() {
        return WarehouseStock.builder()
                .id(null)
                .amount(amount)
                .pct(pct)
                .price(price)
                .stockValue(stockValue)
                .period(period)
                .articleId(articleId)
                .build();
    }
}
