package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibsys.ibsysbackend.dto.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class ImportData {
    private Integer game;
    private Integer group;
    private Integer period;
    @JsonProperty("forecast")
    private ForecastDto forecastDto;
    private WarehouseStock warehousestock;
    private List<AppOrderDto> inwardstockmovement;
    private List<FutureInwardStockmovement> futureinwardstockmovement;
    private IdleTimeCostDto idletimecosts;
    private List<WorkPlaceDto> waitinglistworkstations;
    private List<MissingPartDto> waitingliststock;
    private List<OrdersInWorkPlaceDto> ordersinwork;
    private List<AppOrderDto> completedorders;
    private CycletimesDto cycletimes;
}