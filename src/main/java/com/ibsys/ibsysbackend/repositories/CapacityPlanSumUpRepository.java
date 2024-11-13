package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.CapacityPlanSumUp;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CapacityPlanSumUpRepository extends JpaRepository<CapacityPlanSumUp, UUID> {
}
