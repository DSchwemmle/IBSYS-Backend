package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AppOrder {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
    private Integer idFromXml;
    private Integer period;
    private Integer quantity;
    private Integer mode;
    private Integer amount;
    private Integer time;
    private Double materialCosts;
    private Double orderCosts;
    private Double entireCosts;
    private Double pieceCosts;
    private Integer item;
    private Double cost;
    private Integer startTime;
    private Integer finishTime;
    private Integer cycleTimeMin;
    private Double cycleTimeFactor;
    private Double averageUnitCosts;
    private Boolean isInwardStockMovement;
    private Boolean isFutureInwardStockMovement;
    private Boolean isCompletedOrders;
    private Boolean isCycleTime;
    private Integer articleId;
}