package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.entities.MaterialPlan;
import com.ibsys.ibsysbackend.services.MaterialPlanService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.MATERIAL_PLAN)
public class MaterialPlanController {

    private final MaterialPlanService materialPlanService;

    @GetMapping
    List<MaterialPlan> findAll() {
        return materialPlanService.findAll();
    }

    @PutMapping
    void update(@RequestBody final MaterialPlan materialPlan) {
        materialPlanService.update(materialPlan);
    }
}
