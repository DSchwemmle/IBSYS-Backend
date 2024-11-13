package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.entities.CapacityPlanSumUp;
import com.ibsys.ibsysbackend.repositories.CapacityPlanSumUpRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class CapacityPlanSumUpService {

    private final CapacityPlanSumUpRepository capacityPlanSumUpRepository;

    public List<CapacityPlanSumUp> findAll() {
        return capacityPlanSumUpRepository.findAll();
    }
}
