package com.ibsys.ibsysbackend.dto;

import com.ibsys.ibsysbackend.entities.AppOrder;

public record AppOrderDto(
        Integer idFromXml,
        Integer period,
        Integer quantity,
        Integer mode,
        Integer amount,
        Integer time,
        Double materialCosts,
        Double orderCosts,
        Double entireCosts,
        Double pieceCosts,
        Integer item,
        Double cost,
        Integer startTime,
        Integer finishTime,
        Integer cycleTimeMin,
        Double cycleTimeFactor,
        Double averageUnitCosts,
        Boolean isInwardStockMovement,
        Boolean isFutureInwardStockMovement,
        Boolean isCompletedOrders,
        Boolean isCycleTime,
        Integer articleId
) {
    public AppOrder toAppOrder() {
        return AppOrder.builder()
                .id(null)
                .idFromXml(idFromXml)
                .period(period)
                .quantity(quantity)
                .mode(mode)
                .amount(amount)
                .time(time)
                .materialCosts(materialCosts)
                .orderCosts(orderCosts)
                .entireCosts(entireCosts)
                .pieceCosts(pieceCosts)
                .item(item)
                .cost(cost)
                .startTime(startTime)
                .finishTime(finishTime)
                .cycleTimeMin(cycleTimeMin)
                .cycleTimeFactor(cycleTimeFactor)
                .averageUnitCosts(averageUnitCosts)
                .isInwardStockMovement(isInwardStockMovement)
                .isFutureInwardStockMovement(isFutureInwardStockMovement)
                .isCompletedOrders(isCompletedOrders)
                .isCycleTime(isCycleTime)
                .articleId(articleId)
                .build();
    }
}