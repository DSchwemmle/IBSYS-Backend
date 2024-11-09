package com.ibsys.ibsysbackend.entities;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ibsys.ibsysbackend.dto.AppOrderDto;
import com.ibsys.ibsysbackend.dto.ForecastDto;
import com.ibsys.ibsysbackend.entities.importentities.*;
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
    private IdleTimeCostsDto idletimecosts;
    private List<WorkPlaceDto> waitinglistworkstations;
    private List<MissingPartDto> waitingliststock;
    private List<OrdersInWorkWorkplaceDTO> ordersinwork;
    private List<AppOrderDto> completedorders;
    private CycletimesDTO cycletimes;
}