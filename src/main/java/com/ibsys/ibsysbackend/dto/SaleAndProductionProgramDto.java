package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.SaleAndProductionProgram;

public record SaleAndProductionProgramDto(
        Integer pN,
        Integer pNPlusOne,
        Integer pNPlusTwo,
        Integer pNPlusThree,
        String article
) {

    public SaleAndProductionProgram toSaleAndProductionProgram() {
        return SaleAndProductionProgram.builder()
                .id(null)
                .pN(pN)
                .pNPlusOne(pNPlusOne)
                .pNPlusTwo(pNPlusTwo)
                .pNPlusThree(pNPlusThree)
                .article(article)
                .build();
    }
}
