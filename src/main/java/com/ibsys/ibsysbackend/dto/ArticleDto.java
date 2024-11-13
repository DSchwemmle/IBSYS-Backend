package com.ibsys.ibsysbackend.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibsys.ibsysbackend.entities.Article;

public record ArticleDto(
        String id,
        Integer amount,
        String number,
        String usedFor,
        String description,
        @JsonProperty("startamount")
        Double startAmount,
        String pct,
        String price,
        @JsonProperty("stockvalue")
        String stockValue,
        Integer period
) {
    public Article toArticle() {
        return Article.builder()
                .id(Integer.parseInt(id))
                .amount(amount)
                .number(number)
                .usedFor(usedFor)
                .description(description)
                .startAmount(startAmount)
                .pct(Double.parseDouble(pct))
                .price(Double.parseDouble(price))
                .stockValue(Double.parseDouble(stockValue))
                .build();
    }
}
