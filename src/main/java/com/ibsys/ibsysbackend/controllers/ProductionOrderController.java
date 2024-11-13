package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.dto.ProductionOrderDto;
import com.ibsys.ibsysbackend.entities.ProductionOrder;
import com.ibsys.ibsysbackend.services.ProductionOrderService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.PRODUCTION_ORDERS)
public class ProductionOrderController {

    private final ProductionOrderService productionOrderService;

    @GetMapping
    List<ProductionOrder> findAll() {
        return productionOrderService.findAll();
    }

    @PostMapping
    void createMany(@RequestBody final List<ProductionOrderDto> productionOrderDtoList) {
        productionOrderService.createMany(productionOrderDtoList);
    }

    @PutMapping
    void update(@RequestBody final ProductionOrder productionOrder) {
        productionOrderService.update(productionOrder);
    }
}
