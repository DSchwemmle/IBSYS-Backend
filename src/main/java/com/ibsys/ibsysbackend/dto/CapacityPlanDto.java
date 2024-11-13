package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.CapacityPlan;

public record CapacityPlanDto(
        Integer articleNumber,
        String article,
        Integer quantity,
        Integer workplace1,
        Integer workplace2,
        Integer workplace3,
        Integer workplace4,
        Integer workplace6,
        Integer workplace7,
        Integer workplace8,
        Integer workplace9,
        Integer workplace10,
        Integer workplace11,
        Integer workplace12,
        Integer workplace13,
        Integer workplace14,
        Integer workplace15
) {

    public CapacityPlan toCapacityPlan() {
        return CapacityPlan.builder()
                .id(null)
                .articleNumber(articleNumber)
                .article(article)
                .quantity(quantity)
                .workplace1(workplace1)
                .workplace2(workplace2)
                .workplace3(workplace3)
                .workplace4(workplace4)
                .workplace6(workplace6)
                .workplace7(workplace7)
                .workplace8(workplace8)
                .workplace9(workplace9)
                .workplace10(workplace10)
                .workplace11(workplace11)
                .workplace12(workplace12)
                .workplace13(workplace13)
                .workplace14(workplace14)
                .workplace15(workplace15)
                .build();
    }
}
