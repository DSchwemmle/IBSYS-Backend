package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.entities.MaterialPlan;
import com.ibsys.ibsysbackend.repositories.MaterialPlanRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class MaterialPlanService {

    private final MaterialPlanRepository materialPlanRepository;

    public List<MaterialPlan> findAll() {
        return materialPlanRepository.findAll();
    }

    public void update(final MaterialPlan materialPlan) {
        materialPlanRepository.save(materialPlan);
    }
}
