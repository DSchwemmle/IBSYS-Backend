package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Integer> {
    List<WorkPlace> findWorkPlacesByPeriodAndIsIdleTimeCosts(Integer period, Boolean isIdleTimeCosts);
    List<WorkPlace> findWorkPlacesByPeriodAndIsOrdersInWork(int period, boolean isOrdersInWork);
}
