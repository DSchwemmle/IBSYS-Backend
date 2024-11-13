package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.entities.CapacityPlanSumUp;
import com.ibsys.ibsysbackend.services.CapacityPlanSumUpService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.CAPACITY_PLAN_SUM_UP)
public class CapacityPlanSumUpController {

    private final CapacityPlanSumUpService capacityPlanSumUpService;

    @GetMapping
    List<CapacityPlanSumUp> findAll() {
        return capacityPlanSumUpService.findAll();
    }
}
