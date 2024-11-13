package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.CapacityPlan;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface CapacityPlanRepository extends JpaRepository<CapacityPlan, UUID> {

    Optional<CapacityPlan> findByArticleNumber(final Integer articleNumber);
}
