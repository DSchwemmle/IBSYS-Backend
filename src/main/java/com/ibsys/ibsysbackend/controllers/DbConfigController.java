package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.entities.WorkPlace;
import com.ibsys.ibsysbackend.repositories.*;
import com.ibsys.ibsysbackend.repositories.*;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.DB_CONFIG)
public class DbConfigController {

    private final ArticleRepository articleRepository;
    //private final CapacityPlanRepository capacityPlanRepository;
    //private final CapacityPlanSumUpRepository capacityPlanSumUpRepository;
    private final ForecastRepository forecastRepository;
    private final OrdersInWorkWorkplaceRepository ordersInWorkWorkplaceRepository;
    private final ProductionOrderRepository productionOrderRepository;
    private final PurchasePartDispositionRepository purchasePartDispositionRepository;
    //private final SaleAndProductionProgramRepository saleAndProductionProgramRepository;
    private final WaitingliststockWaitlinglistRepository waitingliststockWaitlinglistRepository;
    private final WaitinglistWorkplaceRepository waitinglistWorkplaceRepository;
    private final WorkPlaceRepository workPlaceRepository;
    private final WarehouseStockRepository warehouseStockRepository;
    private final AppOrderRepository appOrderRepository;
    private final BatchRepository batchRepository;
    private final MaterialPlanRepository materialPlanRepository;

    @GetMapping("/clear-db")
    void clearDb() {
        articleRepository.deleteAll();
        //capacityPlanRepository.deleteAll();
        //capacityPlanSumUpRepository.deleteAll();
        forecastRepository.deleteAll();
        ordersInWorkWorkplaceRepository.deleteAll();
        productionOrderRepository.deleteAll();
        purchasePartDispositionRepository.deleteAll();
        //saleAndProductionProgramRepository.deleteAll();
        waitingliststockWaitlinglistRepository.deleteAll();
        waitinglistWorkplaceRepository.deleteAll();
        workPlaceRepository.deleteAll();
        warehouseStockRepository.deleteAll();
        appOrderRepository.deleteAll();
        batchRepository.deleteAll();
        materialPlanRepository.deleteAll();
    }
}
