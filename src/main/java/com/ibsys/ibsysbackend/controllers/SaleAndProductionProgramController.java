package com.ibsys.ibsysbackend.controllers;

import com.ibsys.ibsysbackend.controllers.ApiRoutes;
import com.ibsys.ibsysbackend.entities.SaleAndProductionProgram;
import com.ibsys.ibsysbackend.services.SaleAndProductionProgramService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@CrossOrigin(origins = "*")
@RequestMapping(ApiRoutes.SALE_AND_PRODUCTION_PROGRAM)
public class SaleAndProductionProgramController {

    private final SaleAndProductionProgramService saleAndProductionProgramService;

    @GetMapping
    List<SaleAndProductionProgram> findAll() {
        return saleAndProductionProgramService.findAll();
    }

    @PutMapping
    void update(@RequestBody final List<SaleAndProductionProgram> saleAndProductionProgramDtoList) {
        saleAndProductionProgramService.update(saleAndProductionProgramDtoList);
    }
}
