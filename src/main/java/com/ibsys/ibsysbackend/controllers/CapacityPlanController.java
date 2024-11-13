package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.entities.CapacityPlan;
import com.ibsys.ibsysbackend.services.CapacityPlanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.CAPACITY_PLAN)
public class CapacityPlanController {

    private CapacityPlanService capacityPlanService;

    @GetMapping
    List<CapacityPlan> findAll() {
        return capacityPlanService.findAll();
    }
}
