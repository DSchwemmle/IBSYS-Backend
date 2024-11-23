package com.ibsys.ibsysbackend.repositories;

import com.ibsys.ibsysbackend.entities.WorkPlace;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkPlaceRepository extends JpaRepository<WorkPlace, Integer> {

    List<WorkPlace> findWorkPlacesByPeriod(final Integer period);

    @Query("SELECT wp FROM work_place wp where wp.period = :period and wp.isIdleTimeCosts = :isIdleTimeCosts")
    List<WorkPlace> findWorkPlacesByPeriodAndIsIdleTimeCosts(
            @Param("period") final int period,
            @Param("isIdleTimeCosts") final boolean isIdleTimeCosts
    );

    List<WorkPlace> findWorkPlacesByPeriodAndIsOrdersInWork(final int period, final boolean isOrdersInWork);
}
