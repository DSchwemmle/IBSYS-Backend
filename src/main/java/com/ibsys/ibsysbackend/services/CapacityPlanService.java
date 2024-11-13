package com.ibsys.ibsysbackend.services;

import com.ibsys.ibsysbackend.dto.CapacityPlanDto;
import com.ibsys.ibsysbackend.entities.CapacityPlan;
import com.ibsys.ibsysbackend.repositories.CapacityPlanRepository;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
@Slf4j
public class CapacityPlanService {

    private final CapacityPlanRepository capacityPlanRepository;

    public List<CapacityPlan> findAll() {
        return capacityPlanRepository.findAll();
    }

    public void createOne(final CapacityPlanDto capacityPlanDto) {
        var capacityPlan = capacityPlanDto.toCapacityPlan();
        capacityPlanRepository.save(capacityPlan);
    }

    public void createMany(final List<CapacityPlanDto> capacityPlanDtoList) {
        var capacityPlanList = capacityPlanDtoList.stream()
                .map(CapacityPlanDto::toCapacityPlan)
                .toList();
        capacityPlanRepository.saveAll(capacityPlanList);
    }

}
