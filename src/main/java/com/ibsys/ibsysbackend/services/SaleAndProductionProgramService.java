package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.entities.SaleAndProductionProgram;
import com.ibsys.ibsysbackend.repositories.SaleAndProductionProgramRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class SaleAndProductionProgramService {

    private final SaleAndProductionProgramRepository saleAndProductionProgramRepository;
    private final ProductionOrderService productionOrderService;

    public List<SaleAndProductionProgram> findAll() {
        return saleAndProductionProgramRepository.findAll();
    }

    public void update(final List<SaleAndProductionProgram> saleAndProductionProgramList) {
        saleAndProductionProgramRepository.saveAll(saleAndProductionProgramList);
        productionOrderService.updateProductionOrderOfAllArticles();
    }
}
